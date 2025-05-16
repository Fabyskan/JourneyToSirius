package sirius;

import java.util.Scanner;

public class Player extends Actor implements ProjectileEmitter{

    double timePassed = 2;

    public void setTimePassed(double timePassed) {
        this.timePassed = timePassed;
    }

    public Player(){
        super(SiriusGUI.WIDTH/2,(SiriusGUI.HEIGHT -50));

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
                if((getX() -100*deltaTime) < 0){
                    break;
                }
                setX(getX() - 100*deltaTime);
                break;
            case RIGHT:
                if((getX() + 100*deltaTime) > SiriusGUI.WIDTH){
                    break;
                }
                setX(getX() + 100*deltaTime);
                break;
        }
    }

    @Override
    public Projectile emit(double time) {
        if (time >= timePassed) {
            Projectile shoot = new Projectile(getX(), (getY()-16), true);
            setTimePassed(time+2);
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