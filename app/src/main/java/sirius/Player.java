package sirius;

import java.util.Scanner;

public class Player extends Actor{

    public Player(){
        super(SiriusGUI.WIDTH/2,(SiriusGUI.HEIGHT -50));

    }

    /**
     * Ich check es nicht. Was ist die Benutzereingabe? Doch nicht irgendwas mit Scanner?
     * Das macht irwie keinen Sinn. Immerhin wird in SiriusGUI schon etwas in die Richtung gemacht?
     * @return Gibt einen der drei Strings aus
     */
    public String getImage(){
       /* String x = null;
        Scanner scan = new Scanner(System.in);
        x = scan.nextLine().toUpperCase();
        return switch (x) {
            case "A" -> "player-left";
            case "D" -> "player-right";
            default -> "player-none";
        };*/
        return switch (this) {
            case PlayerInput.LEFT -> "player-left";
            case PlayerInput.RIGHT -> "player-right";
            default -> "player-none";
        };
    }

    public void update(double time, double deltaTime){
        PlayerInput input = PlayerInput.toInput(getImage());
        switch(input) {
            case PlayerInput.LEFT:
                if((getX() -100*deltaTime) < 0){
                    break;
                }
                setX(getX() - 100*deltaTime);
                break;
            case PlayerInput.RIGHT:
                if((getX() + 100*deltaTime) > SiriusGUI.WIDTH){
                    break;
                }
                setX(getX() + 100*deltaTime);
                break;
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