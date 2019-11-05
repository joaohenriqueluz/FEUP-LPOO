package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;
import lpoo39.Logic.Position;

import java.awt.*;

public class HeroSwing implements DrawingElement {

    private Image lookUp, lookDown, lookLeft, lookRight;
    private Position position;
    private String healthGraph;
    private char direction;

    private int ammo, bricks, score, health, ratio,width;

    public HeroSwing() {
    }

    public void setLookUp(Image lookUp) {
        this.lookUp = lookUp;
    }

    public void setLookDown(Image lookDown) {
        this.lookDown = lookDown;
    }

    public void setLookLeft(Image lookLeft) {
        this.lookLeft = lookLeft;
    }

    public void setLookRight(Image lookRight) {
        this.lookRight = lookRight;
    }

    @Override
    public void setParameters(Object... pars) {
        position = (Position) pars[0];
        direction = (char) pars[2];
        score = (int) pars[3];
        health = (int) pars[5];
        ammo = (int) pars[7];
        bricks = (int) pars[9];
        healthGraph = (String) pars[11];

        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
        width = Game.getGraphic().getWidth();
    }

    @Override
    public void draw() {
        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getGraphics();
        switch (direction) {
            case 'R':
                g2d.drawImage(lookRight, position.getX() * ratio, position.getY() * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'L':
                g2d.drawImage(lookLeft, position.getX() * ratio, position.getY() * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'U':
                g2d.drawImage(lookUp, position.getX() * ratio, position.getY() * ratio, 2 * ratio, 2 * ratio, null);
                break;

            case 'D':
                g2d.drawImage(lookDown, position.getX() * ratio, position.getY() * ratio, 2 * ratio, 2 * ratio, null);
                break;
            default:
                g2d.drawImage(lookRight, position.getX() * ratio, position.getY() * ratio, ratio, ratio, null);
                break;

        }
        drawStats(g2d);
    }

    private void drawStats(Graphics2D g) {
            Font f = new Font("Monospaced", Font.BOLD, 2*ratio/3);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Score: " + score, ratio, ratio - 8);

            g.setColor(Color.red);
            g.drawString("Health: " + healthGraph + "(" + health + ") ", 2*(width* ratio)/10, ratio - 8);

            g.setColor(Color.yellow);
            g.drawString("Ammo: " + ammo, 5*(width* ratio)/10, ratio - 8);

            g.setColor(Color.GREEN);
            g.drawString("Bricks: " + bricks, 7*(width* ratio)/10, ratio - 8);

            g.setColor(Color.MAGENTA);
            g.drawString("Wave: " + Game.getMap().getWave(), 9*(width* ratio)/10, ratio - 8);

    }
}
