package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.UniObjectLanterna;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBrickPack {
    Hero hero;
    @Before
    public void setUp(){
        hero = new Hero (new Position(3,2));
    }

    @Test
    public void affectHeroTest(){
        BrickPack bp = new BrickPack(hero,new Position (23,3),14);
        assertEquals(14, bp.getQuantity());
        
        bp.affectHero();
        assertEquals(114, hero.getBricks());
    }

    @Test
    public void getterDescription(){
        BrickPack bp = new BrickPack(hero,new Position (23,3),14);
        assertEquals("How amazing 14 bricks!", bp.getDescription());
    }

    @Test
    public void startDrawingTest() throws Exception {
        BrickPack brickPack = new BrickPack(hero, new Position(7,2), 10);
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        brickPack.startDraw();

        DrawingElement de = brickPack.getDrawingElement();
        assertNotEquals(null, de);

        UniObjectLanterna ul = (UniObjectLanterna) de;
        assertEquals(new Position (7,2), ul.getPosition());
        assertEquals("#FFFF33", ul.getColor());
        assertEquals("█", ul.getCharacter());
    }
}
