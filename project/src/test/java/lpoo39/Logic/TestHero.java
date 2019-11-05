package lpoo39.Logic;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.Lanterna.HeroLanterna;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestHero {
    
    @Test
    public void testMove(){
        Hero hero = new Hero(new Position(10, 10));
        hero.setPosition(hero.moveRight());
        assertEquals(hero.getPosition(), new Position(11,10));
        hero.setPosition(hero.moveUp());
        assertEquals(hero.getPosition(), new Position(11,9));
        hero.setPosition(hero.moveLeft());
        assertEquals(hero.getPosition(), new Position(10,9));
        hero.setPosition(hero.moveDown());
        assertEquals(hero.getPosition(), new Position(10,10));
    }


    @Test
    public void testBullets(){
        Hero hero = new Hero(new Position(10,10));
        int ammo = hero.getAmmo();

        for(int i  = ammo + 5 ; i > 0; i--){
            hero.fire();
        }
        assertTrue(hero.getAmmo() == 0);

        hero.reload(5);
        assertEquals(hero.getAmmo(),5);

        hero.fire();
        hero.fire();
        hero.fire();
        hero.fire();
        assertEquals(hero.getAmmo(),1);

        hero.fire();
        assertTrue(!hero.fire());
        assertEquals(hero.getAmmo(),0);
    }



    @Test
    public void testBricks(){
        Hero hero = new Hero(new Position(10,10));
        int bricks = hero.getBricks();

        for(int i  = bricks + 5 ; i > 0; i--){
            hero.build();
        }
        assertTrue(hero.getBricks() == 0);

        hero.reloadBricks(5);
        assertEquals(hero.getBricks(),5);

        hero.build();
        hero.build();
        hero.build();
        hero.build();
        assertEquals(hero.getBricks(),1);

        hero.build();
        assertTrue(!hero.build());
        assertEquals(hero.getBricks(),0);
    }

    @Test
    public void testScore() {
    Hero hero = new Hero(new Position (10,10));
    hero.scored(10);
        assertEquals(hero.getScore(),10);
    }

    @Test
    public void testHealthGraph() {
        Hero hero = new Hero(new Position (10,10));
        hero.getsHurt(25);
        assertEquals("♥♥♥♥",hero.healthGraph());

        hero.getsHurt(25);
        assertEquals("♥♥♥",hero.healthGraph());

        hero.getsHurt(25);
        assertEquals("♥♥",hero.healthGraph());

        hero.getsHurt(25);
        assertEquals("♥",hero.healthGraph());

    }

    @Test
    public void startDrawingTest() throws Exception {
        Hero hero = new Hero(new Position(10,10));
        Game game = Game.getInstance();
        game.setGameParams(50, 40, "Lanterna");
        hero.startDraw();

        DrawingElement de = hero.getDrawingElement();
        assertNotEquals(null, de);

        HeroLanterna hl = (HeroLanterna) de;
        assertEquals(new Position(10, 10), hl.getPosition());
        assertEquals("#00FF00", hl.getHeroColor());
        assertEquals(100, hl.getAmmo());
        assertEquals(100, hl.getBricks());
        assertEquals(0, hl.getScore());
    }

}
