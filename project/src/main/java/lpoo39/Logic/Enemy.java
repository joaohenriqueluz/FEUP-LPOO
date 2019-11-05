package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class Enemy extends Character {

    public Enemy(int health, int attack, Position position) {
        super(health, attack, position, 'R');
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#FF3333", "M", getDirection());
    }

    @Override
    public void endDraw() {

    }
}
