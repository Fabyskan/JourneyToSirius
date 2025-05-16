package sirius;

import java.util.Random;

public class OpponentLurker extends Opponent implements ProjectileEmitter{
    private double timePassed = 2;
    private double richtung = 1;

    public void setRichtung(double richtung) {
        this.richtung = richtung;
    }

    public double getRichtung() {
        return richtung;
    }

    public OpponentLurker(double x) {
        super(x);

    }

    public void setTimePassed(double timePassed) {
        this.timePassed = timePassed;
    }

    @Override
    public String getImage() {
        return "opponent-lurker";
    }

    @Override
    public void update(double time, double deltaTime) {
        double placeToBe = new Random().nextDouble((double)SiriusGUI.HEIGHT/2);
        if(getY() < placeToBe){
            setY(Math.min(getY() + deltaTime*80,placeToBe));
        }
        double stopL = 50;
        double stopR = SiriusGUI.WIDTH - 50;
        //double mid = SiriusGUI.WIDTH/2;

        if(getX() <= stopL){
            setX(getX() + deltaTime*80);
            richtung = 1;
        }
        else if(getX() >= stopR){
            setX(getX() - deltaTime*80);
            richtung = -1;
        }
        else {
            if(richtung == 1){
                setX(Math.min(getX() + deltaTime*80,stopR));
            }else
                setX(Math.max(getX() - deltaTime*80,stopL));
        }
    }

    @Override
    public Projectile emit(double time) {
        if (time >= timePassed) {
            Projectile shoot = new Projectile(getX(), (getY()+16), false);
            setTimePassed(time+2);
            return shoot;
        } else {
            return null;
        }
    }
}
