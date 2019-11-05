package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;
import lpoo39.Logic.Position;

import java.awt.*;

public class UniObjectSwing implements DrawingElement {

    private Position position;
    private Image image;
    private int ratio;

    public UniObjectSwing() {
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setParameters(Object... pars) {
        position = (Position) pars[0];
        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
    }

    @Override
    public void draw() {
        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getGraphics();
        g2d.drawImage(image, position.getX() * ratio, position.getY() * ratio, ratio, ratio, null);
    }
}
