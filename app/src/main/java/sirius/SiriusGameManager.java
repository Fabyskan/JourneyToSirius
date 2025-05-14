package sirius;

public class SiriusGameManager {

  private SiriusGUI siriusGUI;

  public SiriusGameManager(){
    // DONT'T CHANGE
    this.siriusGUI = new SiriusGUI(this);
    // /DONT'T CHANGE
    
  }

  public void update(double time, double deltaTime){

  }


  /* #actor
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
  */
  
}
