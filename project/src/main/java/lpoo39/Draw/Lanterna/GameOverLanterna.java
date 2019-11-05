package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GameOverLanterna implements DrawingElement {

    private Position position;
    private String color;
    private int score;
    private long survivedTime;

    private TerminalScreen screen;
    private TextGraphics graphics;

    public GameOverLanterna(TerminalScreen screen) {
        this.screen = screen;
        this.graphics = this.screen.newTextGraphics();
    }

    public Position getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public long getSurvivedTime() {
        return survivedTime;
    }

    @Override
    public void setParameters(Object... pars) {
        this.position = (Position) pars[0];
        this.color = (String) pars[1];
        this.score = (int) pars[2];
        this.survivedTime = (long) pars[3];
    }

    @Override
    public void draw() {
        screen.clear();
        graphics.setForegroundColor(TextColor.Factory.fromString(color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX()-2, position.getY(), "   ____                         ___                 ");
        graphics.putString(position.getX()-2, position.getY() + 1, "  / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ ");
        graphics.putString(position.getX()-2, position.getY() + 2, " | |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|");
        graphics.putString(position.getX()-2, position.getY() + 3, " | |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |   ");
        graphics.putString(position.getX()-2, position.getY() + 4, "  \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   ");

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX() + 10, position.getY() + 7, "Score: " + score);
        graphics.putString(position.getX() + 10, position.getY() + 8, "Seconds Survived: " + survivedTime / 1000);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
