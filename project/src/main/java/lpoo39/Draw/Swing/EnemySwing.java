package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;
import lpoo39.Logic.Position;

import java.awt.*;

public class EnemySwing implements DrawingElement {

    private Position position;
    private Image lookRight, lookLeft, lookUp, lookDown;
    private char direction;
    private int ratio;


    @Override
    public void setParameters(Object... pars) {
        position = (Position) pars[0];
        direction = (char) pars[3];
        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
    }

    public void setLookRight(Image lookRight) {
        this.lookRight = lookRight;
    }

    public void setLookLeft(Image lookLeft) {
        this.lookLeft = lookLeft;
    }

    public void setLookUp(Image lookUp) {
        this.lookUp = lookUp;
    }

    public void setLookDown(Image lookDown) {
        this.lookDown = lookDown;
    }


    @Override
    public void draw() {
        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getGraphics();
        switch (direction) {
            case 'R':
                g2d.drawImage(lookRight, (position.getX()) * ratio, (position.getY()) * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'L':
                g2d.drawImage(lookLeft, (position.getX()) * ratio, (position.getY()) * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'U':
                g2d.drawImage(lookUp, (position.getX()) * ratio, (position.getY()) * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'D':
                g2d.drawImage(lookDown, (position.getX()) * ratio, (position.getY()) * ratio, 2 * ratio, 2 * ratio, null);
                break;
            default:
                g2d.drawImage(lookRight, (position.getX()) * ratio, (position.getY()) * ratio, 2 * ratio, 2 * ratio, null);
                break;

        }
    }
}

