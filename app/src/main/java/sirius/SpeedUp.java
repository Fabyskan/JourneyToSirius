package sirius;

public class SpeedUp extends PowerUp {
    public SpeedUp(double x, double y) {
        super(x,y);
    }

    @Override
    public String getImage() {
        return "powerup-speed";
    }

    @Override
    public void update(double time, double deltaTime) {
        changeY(deltaTime*100);
    }

    @Override
    public void upgrade(Player player) {
        player.setModifier(player.getModifier() + 0.01);
        System.out.println("Du wirst schneller?");
    }
}
