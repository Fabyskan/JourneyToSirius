package sirius;

public abstract class PowerUp extends Actor implements Upgradable{
    public PowerUp(double x, double y){
        super(x,y);
    }

    public abstract void upgrade(Player player);
}
