package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestWall {
    @Test
    public void createWall (){
        Wall wall = new Wall(new Position(2,3));
        assertEquals(new Position(2,3), wall.getPosition());
    }

    @Test
    public void createWrongWall (){
        Wall wall = new Wall(new Position(2,3));
        assertNotEquals(new Position(5,3), wall.getPosition());
    }

    @Test
    public void startDrawingTest() throws Exception {
        Wall wall = new Wall(new Position(2, 2));
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        wall.startDraw();

        DrawingElement de = wall.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position(2, 2), (ul.getPosition()));
        assertEquals("#FFFFFF", ul.getColor());
        assertEquals("#", ul.getCharacter());
    }
}
