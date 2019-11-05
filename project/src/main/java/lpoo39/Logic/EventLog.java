package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class EventLog extends Element {
    private int height;
    private int width;

    private String message;

    public EventLog() {
        super(new Position(0, 0));
        this.height = 20;
        this.width = 30;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(width, height, "#FFFFFF", message);
    }

    @Override
    public void endDraw() {

    }
}
