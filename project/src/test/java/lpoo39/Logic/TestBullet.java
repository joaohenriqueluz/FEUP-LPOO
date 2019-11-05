package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBullet {
    Bullet blt;

    @Before
    public void setBullet(){
        blt = new Bullet(new Position(5,6), 'R');
    }

    @Test
    public void createBullet (){
        Bullet bullet = new Bullet(new Position(5,6), 'R');
        Position position = new Position(5,6);
        assertTrue(position.equals(bullet.getPosition()) && bullet.getDirection() == 'R');
    }

    @Test
    public void createWrongBullet (){
        Bullet b = new Bullet(new Position(3,4), 'U');
        assertFalse(new Position(2,3)== b.getPosition() && b.getDirection() == 'D');
    }

    @Test
    public void setterDirection(){
        blt.setDirection('D');
        assertTrue(blt.getDirection() == 'D');
    }

    @Test
    public void moveBulletRight(){

        assertEquals(new Position(6,6), blt.move());
    }

    @Test
    public void moveBulletLeft(){
        blt.setDirection('L');
        assertEquals(new Position(4,6), blt.move());
    }

    @Test
    public void moveBulletUp(){
        blt.setDirection('U');
        assertEquals(new Position(5,5), blt.move());
    }

    @Test
    public void moveBulletDown(){
        blt.setDirection('D');
        assertEquals(new Position(5,7), blt.move());
    }

    @Test
    public void moveBulletWrong(){
        blt.setDirection('T');
        assertEquals(new Position(5,6), blt.move());
    }

    @Test
    public void startDrawingTest() throws Exception {
        Bullet bullet = new Bullet(new Position(10,12), 'R');
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        bullet.startDraw();

        DrawingElement de = bullet.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position (10,12), ul.getPosition());
        assertEquals("#33FF33", ul.getColor());
        assertEquals("*", ul.getCharacter());
    }
}
