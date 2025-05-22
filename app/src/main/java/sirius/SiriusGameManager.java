package sirius;

import java.util.Random;

public class SiriusGameManager {
  private Player player;
  private SiriusGUI siriusGUI;
  private int killCount = 0;

  public SiriusGameManager(){
    // DONT'T CHANGE
    this.siriusGUI = new SiriusGUI(this);
    // /DONT'T CHANGE

    this.player = new Player();
    addActor(player);
  }

  /**
   * Diese Methode soll alle Actors regelmäßig aktualisieren
   * @param time Zeit seit Start des Spiels
   * @param deltaTime Zeit seit der letzten Aktualisierung
   */
  public void update(double time, double deltaTime){
    for(int i = 0; i < actors.length; i ++) {
      if (actors[i] != null)
      {
        actors[i].update(time, deltaTime);
        if(actors[i] instanceof ProjectileEmitter){
          Projectile shot = ((ProjectileEmitter) actors[i]).emit(time);
          if(shot != null){
            addActor(shot);
          }
        }
        siriusGUI.draw(actors[i].getImage(),actors[i].getX(),actors[i].getY());
      }
    }
    if(spawnOpponent()){
      int chance = new Random().nextInt(3);
      double placeToBe = new Random().nextDouble(SiriusGUI.WIDTH);
      Actor goblin = new OpponentSimple(placeToBe);
      Actor moblin = new OpponentChaser(player,placeToBe);
      Actor lizalfos = new OpponentLurker(placeToBe);
      switch (chance){
        case 0 -> addActor(goblin);
        case 1 -> addActor(moblin);
        case 2 -> addActor(lizalfos);
      }
    }
    if(killCount >= 3){
      int chancePwUp = new Random().nextInt(2);
      Actor SpeedUp = new SpeedUp(250, 0);
      Actor WeaponUp = new WeaponUp(250, 0);
      switch (chancePwUp){
        case 0 -> addActor(SpeedUp);
        case 1 -> addActor(WeaponUp);
      }
      killCount = 0;
    }
    for(int i = 0; i < actors.length; i ++) {
      if (actors[i] != null) {
        if (actors[i].getY() < 0 || actors[i].getY() > SiriusGUI.HEIGHT) {
          actors[i] = null;
        }
      }
    }
    for(int i = 0; i < actors.length; i ++) {
      for(int j = 0; j < actors.length; j ++) {
        if (actors[i] != null && actors[j] != null) {
          if (actors[i] != actors[j]) {
            if(Math.pow(actors[i].getX()-actors[j].getX(),2)+Math.pow(actors[i].getY()-actors[j].getY(),2)<=64){

              if((actors[i] instanceof PowerUp) && (actors[j] instanceof Player)){
                ((Upgradable) actors[i]).upgrade((Player) actors[0]);
                actors[i] =null;
              }

              if((actors[j] instanceof PowerUp) && (actors[i] instanceof Player)){
                ((Upgradable) actors[j]).upgrade((Player) actors[0]);
                actors[j] =null;
              }

              if(actors[i] instanceof Projectile){
                actors[i] = null;
                if(actors[j] instanceof Player){
                  actors[j] =null;
                }
              }

              if(actors[j] instanceof Projectile){
                actors[j] = null;
                if(actors[i] instanceof Player){
                  actors[i] =null;
                }
              }

            //  if(actors[i] instanceof PowerUp && actors[j] instanceof Projectile){}
              if(actors[i] instanceof Opponent) {
                {
                  killCount += 1;
                  actors[i] = null;
                  if( actors[j] instanceof Player){
                    actors[j] = null;
                  }
                  System.out.println(killCount);
                }
              }

              if(actors[j] instanceof Opponent) {
                {
                  killCount += 1;
                  actors[j] = null;
                  if( actors[i] instanceof Player){
                    actors[i] = null;
                  }
                  System.out.println(killCount);
                }
              }

        //      actors[i] = null;
          //    actors[j] = null;
              if (actors[0] == null) {
                siriusGUI.gameOver();
              }
            }
          }
        }
      }
    }
  }


  // #actor
  private double nextSpawnTime = 0d;
  private Actor[] actors = new Actor[1024];

  private boolean spawnOpponent(){
    double time = siriusGUI.getTime();
    if (time > nextSpawnTime){
      nextSpawnTime = time + Math.max(0.5d, 8d - 8*Math.pow(time/120, 0.9));
      return true;
    } else
      return false;
  }

  private void addActor(Actor actor){
    if (actor == null)
      throw new IllegalArgumentException("actor must not be null");

    for (int i = 0; i < actors.length; i++){
      if (actors[i] == null){
        actors[i] = actor;
        break;
      }
    }
  }


}
