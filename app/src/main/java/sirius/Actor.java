package sirius;

import java.awt.*;

public abstract class Actor {
    private double x;
    private double y;

    public Actor(double x, double y) {
        if(x < 0 || y < 0 && x > SiriusGUI.WIDTH || y > SiriusGUI.HEIGHT) {
            throw new ArithmeticException();
        }
        this.x = x;
        this.y = y;
    }

    public abstract String getImage();

    public abstract void update(double time, double deltaTime);

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void changeX(double deltaX) {
        x += deltaX;
    }

    public void changeY(double deltaY) {
        y += deltaY;
    }
}
