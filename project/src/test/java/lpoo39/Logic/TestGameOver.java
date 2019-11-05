package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.GameOverLanterna;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGameOver {
    @Test
    public void startDrawingTest() throws Exception {
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        GameOver gameOver = Game.getGameOver();
        gameOver.setScore(60);
        gameOver.setSurvivedTime(10);
        gameOver.startDraw();

        DrawingElement de = gameOver.getDrawingElement();
        assertNotEquals(null, de);

        GameOverLanterna gol = (GameOverLanterna) de;
        assertEquals(new Position(25, 10), gol.getPosition());
        assertEquals("#FF00FF", gol.getColor());
        assertEquals(60, gol.getScore());
        assertEquals(10, gol.getSurvivedTime());
    }
}
