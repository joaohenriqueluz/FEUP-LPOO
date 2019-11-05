package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventLogSwing implements DrawingElement {


    private int width, height, ratio;
    private String color;
    private List<String> messages;

    public EventLogSwing() {
        messages = new ArrayList<>();
    }


    @Override
    public void setParameters(Object... pars) {
        width = (int) pars[0];
        height = (int) pars[1];
        color = (String) pars[2];
        parseMessage((String) pars[3]);
        ratio = ((SwingFactory) Game.getGraphic()).getRatio();
    }

    private void parseMessage(String msg) {
        if (msg != "") {
            if (messages.size() > height + 1) {
                messages.clear();
            }
            messages.add(msg);
            Game.getLog().setMessage("");
        }
    }

    @Override
    public void draw() {

        Graphics2D g2d = (Graphics2D) ((SwingFactory) Game.getGraphic()).getLogGraphics();
        g2d.setColor(new Color(0, 0, 0));
        g2d.drawRect(0, 0, width * ratio, height * ratio);
        g2d.fillRect(0, 0, width * ratio, height * ratio);

        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.setColor(new Color(255, 255, 255));

        int line = 25;
        for (String m : messages) {
            g2d.drawString(m, 15, line);
            line += 20;
        }
    }
}
