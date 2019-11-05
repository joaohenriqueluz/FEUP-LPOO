package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class Hero extends Character {

    private int ammo, bricks, score;

    public Hero(Position position) {
        super(100, 10, position, 'R');
        ammo = 100;
        bricks = 100;
        score = 0;

    }

    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#00FF00", getDirection(), score, "#FFFF00", getHealth(), "#FF0000", ammo, "#0000FF", bricks, "#00FF00", healthGraph());
    }

    @Override
    public void endDraw() {
    }

    public boolean fire() {
        if (ammo > 0) {
            ammo--;
            return true;
        } else
            return false;
    }

    public void reload(int ammount) {
        ammo += ammount;
    }

    public boolean build() {
        if (bricks > 0) {
            bricks--;
            return true;
        } else
            return false;
    }

    public void reloadBricks(int ammount) {
        bricks += ammount;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getBricks() {
        return bricks;
    }

    public void scored(int i) {
        score += i;
    }

    public int getScore() {
        return score;
    }

    public String healthGraph() {
        if (getHealth() >= 75) return "♥♥♥♥";
        else if (getHealth() >= 50) return "♥♥♥";
        else if (getHealth() >= 25) return "♥♥";
        else return "♥";
    }
}
