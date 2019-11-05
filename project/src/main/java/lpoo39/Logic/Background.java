package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class Background extends Element {

    private int width, height;

    public Background(int width, int height) {
        super(new Position(1, 1));
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getInstance().getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters("#000000", width, height);
    }

    @Override
    public void endDraw() {

    }
}
