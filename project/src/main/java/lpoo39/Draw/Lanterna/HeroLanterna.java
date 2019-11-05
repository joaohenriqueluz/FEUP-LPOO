package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import lpoo39.Logic.Game;
import lpoo39.Logic.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class HeroLanterna implements DrawingElement {
    private Position position;
    private String heroColor, scoreColor, healthColor, ammoColor, brickColor, healthGraph;
    private char direction;

    private int ammo, bricks, score, health, width;

    private TerminalScreen screen;
    private TextGraphics graphics;

    public HeroLanterna(TerminalScreen screen) {
        this.screen = screen;
        this.graphics = this.screen.newTextGraphics();
        width = Game.getGraphic().getWidth();

    }

    @Override
    public void setParameters(Object... pars) {
        position = (Position) pars[0];
        heroColor = (String) pars[1];
        direction = (char) pars[2];

        score = (int) pars[3];
        scoreColor = (String) pars[4];
        health = (int) pars[5];
        healthColor = (String) pars[6];
        ammo = (int) pars[7];
        ammoColor = (String) pars[8];
        bricks = (int) pars[9];
        brickColor = (String) pars[10];
        healthGraph = (String) pars[11];

    }

    public Position getPosition() {
        return position;
    }

    public String getHeroColor() {
        return heroColor;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getBricks() {
        return bricks;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void draw() {
        this.graphics.setForegroundColor(TextColor.Factory.fromString(heroColor));
        this.graphics.enableModifiers(SGR.BOLD);

        switch (direction) {
            case 'R':
                this.graphics.putString(new TerminalPosition(position.getX(), position.getY()), "►");
                break;

            case 'L':
                this.graphics.putString(new TerminalPosition(position.getX(), position.getY()), "◄");
                break;

            case 'U':
                this.graphics.putString(new TerminalPosition(position.getX(), position.getY()), "▲");
                break;

            case 'D':
                this.graphics.putString(new TerminalPosition(position.getX(), position.getY()), "▼");
                break;

        }
        drawStats(graphics);
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawStats(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(scoreColor));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(1, 0, "Score: " + score);

        graphics.setForegroundColor(TextColor.Factory.fromString(healthColor));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString( 2*width/10, 0, "Health:");//+ healthGraph() + " ("+ getHealth() + ") ");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(2*width/10 + 7, 0, healthGraph + "(" + health + ") ");

        graphics.setForegroundColor(TextColor.Factory.fromString(ammoColor));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(5*width/10-3, 0, "Ammo: " + ammo);

        graphics.setForegroundColor(TextColor.Factory.fromString(brickColor));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(7*width/10, 0, "Bricks: " + bricks);

        //Wave
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(9*width/10, 0, "Wave: " + Game.getMap().getWave());
    }

}
