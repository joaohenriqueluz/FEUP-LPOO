package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class EventLogLanterna implements DrawingElement {
    private int width, height;
    private String color, message;
    private int line;

    private TerminalScreen screen;
    private TextGraphics graphics;

    public EventLogLanterna(TerminalScreen screen) {
        this.screen = screen;
        graphics = this.screen.newTextGraphics();
        line = 1;
    }

    public String getColor() {
        return color;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setParameters(Object... pars) {
        width = (int) pars[0];
        height = (int) pars[1];
        color = (String) pars[2];
        message = (String) pars[3];
    }

    @Override
    public void draw() {

        try {

            if (line >= height - 1) {
                screen.clear();
                screen.refresh();
                line = 1;
            }
            graphics.setForegroundColor(TextColor.Factory.fromString(color));
            graphics.putString(2, line++, message);
            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
