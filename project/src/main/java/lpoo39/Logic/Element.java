package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;

public abstract class Element {
    protected Position position;

    protected DrawingElement drawingElement;

    public Element(Position position) {
        this.position = position;
    }


    public void setDrawingElement(DrawingElement drawingElement) {
        this.drawingElement = drawingElement;
    }

    public DrawingElement getDrawingElement() {
        return drawingElement;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown() {
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveLeft() {
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }

    public Position moveRight() {
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    public void draw() {
        startDraw();
        drawingElement.draw();
        endDraw();
    }

    public abstract void startDraw();

    public abstract void endDraw();
}
