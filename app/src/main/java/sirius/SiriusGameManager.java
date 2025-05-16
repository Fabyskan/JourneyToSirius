package sirius;

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
    for(int i = 0; i >= 1024; i ++) {
      if (actors[i] != null)
      {
        actors[i].update(time, deltaTime);
        siriusGUI.draw(actors[i].getImage(),actors[i].getX(),actors[i].getY());
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
