package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;

import java.awt.*;

public class BackgroundSwing implements DrawingElement {

    int ratio;

    private int width, height;
    private Image image;

    public BackgroundSwing() {
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setParameters(Object... pars) {
        width = (int) pars[1];
        height = (int) pars[2];
        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
    }

    @Override
    public void draw() {
        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getGraphics();
        g2d.drawImage(image, 0, 0, width * ratio, height * ratio, null);
    }
}
