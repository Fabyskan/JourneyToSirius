package sirius;

import java.util.Scanner;

public class Player extends Actor{

    public Player(){
        super(SiriusGUI.WIDTH/2,(SiriusGUI.HEIGHT -50));

    }

    public String getImage(){
        String x = null;
        Scanner scan = new Scanner(System.in);
        x = scan.nextLine().toUpperCase();
        return switch (x) {
            case "A" -> "player-left";
            case "D" -> "player-right";
            default -> "player-none";
        };
    }

    public void update(double time, double deltaTime){
        PlayerInput input = PlayerInput.toInput(getImage());
        switch(input) {
            case PlayerInput.LEFT:
                setX(getX() - 100*deltaTime);
                break;
            case PlayerInput.RIGHT:
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