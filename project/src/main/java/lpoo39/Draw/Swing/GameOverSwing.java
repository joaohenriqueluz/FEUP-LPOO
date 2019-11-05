package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;
import lpoo39.Logic.Position;

import java.awt.*;

public class GameOverSwing implements DrawingElement {

    private Image image;
    private int score, ratio;
    private long survivedTime;

    public GameOverSwing() {
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setParameters(Object... pars) {
        score = (int) pars[2];
        survivedTime = (long) pars[3] / 1000;
        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
    }

    @Override
    public void draw() {
        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getGraphics();
        g2d.drawImage(image, 0, 0, ratio * 30, ratio * 25, null);
        g2d.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 25));

        g2d.setColor(Color.getHSBColor(152, 88, 155));
        g2d.drawString("Seconds survived: " + survivedTime, ratio * 26, ratio * 10);
        g2d.setColor(Color.getHSBColor(230, 36, 150));
        g2d.drawString("Score: " + score, ratio * 26, ratio * 12);
    }
}
