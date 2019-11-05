package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBrick {

    @Test
    public void createBullet (){
        Brick brick = new Brick(new Position(5,6));
        Position position = new Position(5,6);
        assertTrue(position.equals(brick.getPosition()));
    }

    @Test
    public void createWrongWall (){
        Brick brick = new Brick(new Position(5,6));
        Position position = new Position(10,6);
        assertNotEquals(position, brick.getPosition());
    }

    @Test
    public void startDrawingTest() throws Exception {
        Brick brick = new Brick(new Position(5,6));
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        brick.startDraw();

        DrawingElement de = brick.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position (5,6), ul.getPosition());
        assertEquals("#FFFFFF", ul.getColor());
        assertEquals("█", ul.getCharacter());
    }
}
