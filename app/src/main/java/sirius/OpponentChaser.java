package sirius;

public class OpponentChaser extends Opponent{
    private Player player;
    OpponentChaser(Player player, double x) {
        super(x);
        this.player = player;
    }

    @Override
    public String getImage() {
        return "opponent-chaser";
    }

    @Override
    public void update(double time, double deltaTime) {
        setY(getY() + deltaTime*150);
        if(player.getX() < getX()) {
           setX(getX()-deltaTime*75);
        }
        if(player.getX() > getX()) {
            setX(getX()+deltaTime*75);
        }
    }
}
