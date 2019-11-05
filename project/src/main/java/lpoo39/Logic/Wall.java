package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class Wall extends Element {

    public Wall(Position position) {
        super(position);
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#FFFFFF", "#");
    }

    @Override
    public void endDraw() {

    }
}
