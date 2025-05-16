package sirius;

public class Projectile extends Actor{
    private boolean fromPlayer;

    public Projectile(double x, double y,boolean fromPlayer) {
        super(x, y);
        this.fromPlayer = fromPlayer;
    }

    @Override
    public String getImage() {
        if(fromPlayer){
            return "projectile-player";
        }
        return "projectile-opponent";
    }

    @Override
    public void update(double time, double deltaTime) {
        if(fromPlayer){
            setY(getY() - deltaTime*200);
        }
        else{
            setY(getY() + deltaTime*200);
        }
    }
}
