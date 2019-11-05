package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBulletPack {
    Hero hero;
    @Before
    public void setUp(){
        hero = new Hero (new Position(3,2));
    }

    @Test
    public void affectHeroTest(){
        BulletPack bp = new BulletPack(hero,new Position (7,5),21);
        assertEquals(21, bp.getQuantity());

        bp.affectHero();
        assertEquals(121, hero.getAmmo());
    }

    @Test
    public void getterDescription(){
        BulletPack bp = new BulletPack(hero,new Position (7,5),21);
        assertEquals("How amazing 21 bullets!", bp.getDescription());
    }

    @Test
    public void startDrawingTest() throws Exception {
        BulletPack bulletPack = new BulletPack(hero, new Position(7,2), 10);
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        bulletPack.startDraw();

        DrawingElement de = bulletPack.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position (7,2), ul.getPosition());
        assertEquals("#FFFF33", ul.getColor());
        assertEquals("*", ul.getCharacter());
    }
}
