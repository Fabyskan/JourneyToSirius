package sirius;

import java.util.Scanner;

public class Player extends Actor implements ProjectileEmitter{

    private double timePassed = 2;
    private double modifier = 0;
    private double modifierShot = 1;

    public void setTimePassed(double timePassed) {
        this.timePassed = timePassed;
    }
    public double getModifierShot() {
        return modifierShot;
    }
    public void setModifierShot(double modifierShot) {
        this.modifierShot = modifierShot;
    }

    public double getModifier() {
        return modifier;
    }

    public Player(){
        super(SiriusGUI.WIDTH/2,(SiriusGUI.HEIGHT -50));

    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

    /**
     * Nimmt über getPlayer die Eingabe und gibt dann den entsprechenden String aus
     * @return Gibt einen der drei Strings aus
     */
    @Override
    public String getImage(){
        return switch (SiriusGUI.getPlayerInput()) {
            case LEFT -> "player-left";
            case RIGHT -> "player-right";
            case NONE -> "player-none";
        };
    }
    @Override
    public void update(double time, double deltaTime){
        //PlayerInput input = PlayerInput.toInput(getImage());
        switch(SiriusGUI.getPlayerInput()) {
            case LEFT:
                if((getX() -100*deltaTime+modifier) < 0){
                    break;
                }
                setX(getX() - 100*(deltaTime+modifier));
                break;
            case RIGHT:
                if((getX() + 100*deltaTime+modifier) > SiriusGUI.WIDTH){
                    break;
                }
                setX(getX() + 100*(deltaTime+modifier));
                break;
        }
    }

    @Override
    public Projectile emit(double time) {
        if (time >= timePassed) {
            Projectile shoot = new Projectile(getX(), (getY()-16), true);
            setTimePassed(time+2*modifierShot);
            return shoot;
        } else {
            return null;
        }
    }
}

/*
* if(x.equals("A")) {
            return("player-left");
        }
        if(x.equals("D")) {
            return("player-right");
        }
        else return "player-none";
* */