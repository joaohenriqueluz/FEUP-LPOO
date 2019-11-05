package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class UniObjectLanterna implements DrawingElement {
    private Position position;
    private String color;
    private String character;

    private TerminalScreen screen;
    private TextGraphics graphics;

    public UniObjectLanterna(TerminalScreen screen) {
        this.screen = screen;
        this.graphics = this.screen.newTextGraphics();
    }

    public Position getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public String getCharacter() {
        return character;
    }

    @Override
    public void setParameters(Object... pars) {
        this.position = (Position) pars[0];
        this.color = (String) pars[1];
        this.character = (String) pars[2];
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), character);
    }
}
