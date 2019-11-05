package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.BackgroundLanterna;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBackground {

    @Test
    public void createBackground(){
        Background background = new Background(10,20);
        assertEquals(10, background.getWidth());
        assertEquals(20, background.getHeight());
    }

    @Test
    public void startDrawingTest() throws Exception {
        Background background = new Background(10,20);
        Game game = Game.getInstance();
        game.setGameParams(50,40,"Lanterna");
        background.startDraw();

        DrawingElement de = background.getDrawingElement();
        assertNotEquals(null, de);

        BackgroundLanterna bl = (BackgroundLanterna) de;
        assertEquals(20, bl.getHeight());
        assertEquals(10, bl.getWidth());
    }

}
