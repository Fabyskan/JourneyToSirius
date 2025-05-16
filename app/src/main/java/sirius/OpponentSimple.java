package sirius;

public class OpponentSimple extends Opponent {
    OpponentSimple(double x) {
        super(x);
    }

    @Override
    public String getImage() {
        return "opponent-simple";
    }

    @Override
    public void update(double time, double deltaTime){
        setY(getY() + deltaTime*120);
    }
}
