package lpoo39.Draw.Swing;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private SwingFactory factory;

    public GamePanel(SwingFactory factory) {
        this.factory = factory;
    }

    @Override
    public void paintComponent(Graphics g) {
        factory.setGraphics(g);
        factory.draw();
    }

}
