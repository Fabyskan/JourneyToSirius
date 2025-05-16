package sirius;

import java.util.Random;
import java.util.random.RandomGenerator;

public class SiriusGameManager {
  private Player player;
  private SiriusGUI siriusGUI;

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
      OpponentSimple goblin = new OpponentSimple(placeToBe);
      OpponentChaser moblin = new OpponentChaser(player,placeToBe);
      OpponentLurker lizalfos = new OpponentLurker(placeToBe);
      switch (chance){
        case 0 -> addActor(goblin);
        case 1 -> addActor(moblin);
        case 2 -> addActor(lizalfos);
      }
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
              actors[i] = null;
              actors[j] = null;
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
