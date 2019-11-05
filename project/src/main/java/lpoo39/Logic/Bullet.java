package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class Bullet extends Element {

    private char direction;

    public Bullet(Position position, char dir) {
        super(position);
        direction = dir;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    //Returns next lpoo39.Logic.Position
    public Position move() {
        switch (direction) {
            case 'R':
                return moveRight();

            case 'L':
                return moveLeft();

            case 'U':
                return moveUp();

            case 'D':
                return moveDown();
        }
        return position;
    }

    @Override
    public void startDraw() {
        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#33FF33", "*");
    }

    @Override
    public void endDraw() {

    }
}
