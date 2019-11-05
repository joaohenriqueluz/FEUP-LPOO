package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestEnemy {
    @Test
    public void createEnemy() {
        Enemy enemy = new Enemy(10, 20, new Position(7, 8));
        assertEquals(new Position(7, 8), enemy.getPosition());
    }

    @Test
    public void createWrongEnemy() {
        Enemy enemy = new Enemy(10, 20, new Position(1, 6));
        assertNotEquals(new Position(3, 6), enemy.getPosition());
    }

    @Test
    public void getterAttack() {
        Enemy enemy = new Enemy(10, 20, new Position(1, 6));
        assertEquals(20, enemy.getAttack());
    }

    @Test
    public void startDrawingTest() throws Exception {
        Enemy enemy = new Enemy(13, 25, new Position(12, 9));
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        enemy.startDraw();

        DrawingElement de = enemy.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position(12, 9), ul.getPosition());
        assertEquals("#FF3333", ul.getColor());
        assertEquals("M", ul.getCharacter());
    }
}
