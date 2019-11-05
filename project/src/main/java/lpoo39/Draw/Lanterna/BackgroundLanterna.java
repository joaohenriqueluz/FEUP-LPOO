package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BackgroundLanterna implements DrawingElement {

    private String color;
    private int width, height;

    private TerminalScreen screen;
    private TextGraphics graphics;

    public BackgroundLanterna(TerminalScreen screen) {
        this.screen = screen;
        this.graphics = this.screen.newTextGraphics();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void setParameters(Object... pars) {
        this.color = (String) pars[0];
        this.width = (int) pars[1];
        this.height = (int) pars[2];
    }

    @Override
    public void draw() {
        screen.clear();

        //Background
        graphics.setBackgroundColor(TextColor.Factory.fromString(color));
        graphics.fillRectangle(new TerminalPosition(1, 1), new TerminalSize(width - 1, height - 1), ' ');
    }
}
