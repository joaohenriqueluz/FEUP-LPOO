package lpoo39.Logic;

import lpoo39.Draw.Lanterna.LanternaFactory;
import lpoo39.Draw.Swing.SwingFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGame {
    @Test
    public void singletonInstance(){
        Game game = Game.getInstance();
        assertNotEquals(null, game);

        assertEquals(game, Game.getInstance());
    }

    @Test
    public void gameStateLanterna(){
        Game game = Game.getInstance();
        try {
            game.setGameParams(23,45, "Lanterna");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(Game.stateGame.GAME, Game.getState());
    }

    @Test (expected = Exception.class)
    public void WrongFactory() throws Exception {
        Game game = Game.getInstance();
        game.setGameParams(23,45, "Nothing");
    }
}
