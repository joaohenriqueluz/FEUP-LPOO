package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;

public class GameOver extends Element {

    private int score;
    private long survivedTime;

    public GameOver(Position position) {
        super(position);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSurvivedTime(long survivedTime) {
        this.survivedTime = survivedTime;
    }

    @Override
    public void startDraw() {

        if (drawingElement == null) {
            DrawingGraphic graphic = Game.getGraphic();
            setDrawingElement(graphic.getDrawingObject(this));
        }
        drawingElement.setParameters(position, "#FF00FF", score, survivedTime);
    }

    @Override
    public void endDraw() {

    }

}
