package sirius;

public class WeaponUp extends PowerUp {
    public WeaponUp(double x, double y){
        super(x,y);
    }

    @Override
    public String getImage() {
        return "powerup-weapon";
    }

    @Override
    public void update(double time, double deltaTime) {
        changeY(deltaTime*100);
    }

    @Override
    public void upgrade(Player player) {
        player.setModifierShot(player.getModifierShot() *0.9);
        System.out.println("Triggerhappy");
    }
}
