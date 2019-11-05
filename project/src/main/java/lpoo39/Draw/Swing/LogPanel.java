package lpoo39.Draw.Swing;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {

    private SwingFactory factory;

    public LogPanel(SwingFactory factory) {
        this.factory = factory;
    }

    @Override
    public void paintComponent(Graphics g) {
        factory.setLogGraphics(g);
        factory.logDraw();
    }
}
