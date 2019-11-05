package lpoo39.Logic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestMap {
    Map map;
    Hero hero;

    @Before
    public void setupTests() {
        map = new Map(60, 60, new EventLog());
        hero = map.getHero();
        hero.reload(5);
        hero.reloadBricks(5);
        hero.setDirection('R');
    }

    @Test
    public void TestCreateWalls() {

        int nWalls = (map.getHeight() + map.getWidth()) * 2 - 12;
        List<Wall> walls = map.createWalls();
        assertEquals(walls.size(), nWalls);

        map = new Map(61, 61, new EventLog());
        nWalls = (map.getHeight() + map.getWidth()) * 2 - 12;
        walls = map.createWalls();
        assertEquals(walls.size(), nWalls);

        for (Wall w: walls){
           assertTrue(w.getPosition().getX() >= 0 && w.getPosition().getX() <= 60);
           assertTrue(w.getPosition().getY() >= 1 && w.getPosition().getY() <= 60);
           assertFalse (30 == w.getPosition().getX());
           assertFalse (31 == w.getPosition().getX());
           assertFalse (30 == w.getPosition().getY());
           assertFalse (31 == w.getPosition().getY());
        }

    }

    @Test
    public void TestSpawnEnemies() {
        map.spawnEnemies(3);
        assertEquals(3, map.getEnemies().size());

        map.spawnEnemies(5);
        assertEquals(5, map.getEnemies().size());

    }

    @Test
    public void TestCreateCollectibles() {
        assertTrue(10 >= map.getCollectibles().size());
    }

    @Test
    public void TestProcessKey(){
        Position p = map.getHero().getPosition();
        String key ="DOWN";
        map.processKey(key);
        assertEquals(new Position(p.getX(), p.getY()+1),map.getHero().getPosition());

        key ="UP";
        map.processKey(key);
        assertEquals(new Position(p.getX(), p.getY()),map.getHero().getPosition());

        key ="LEFT";
        map.processKey(key);
        assertEquals(new Position(p.getX()-1, p.getY()),map.getHero().getPosition());

        key ="RIGHT";
        map.processKey(key);
        assertEquals(new Position(p.getX(), p.getY()),map.getHero().getPosition());

        key ="BUILD";
        map.processKey(key);
        assertEquals(1,map.getBricks().size());

        key ="DESTROY";
        map.processKey(key);
        assertEquals(0,map.getBricks().size());

        key ="SHOOT";
        map.processKey(key);
        assertEquals(104,map.getHero().getAmmo()); //100 default + 5 setup - 1 shot;

    }


    @Test
    public void TestDestroyBrick() throws Exception {
        Game game = Game.getInstance();
        game.setGameParams(50,50, "Lanterna");

        Map m = game.getMap();
        Hero h = m.getHero();
        Position p = h.getPosition();

        m.buildBrick();
        m.processKey("LEFT");
        m.buildBrick();

        assertEquals(2, m.getBricks().size());
        m.destroyBrick();
        m.processKey("RIGHT");
        m.destroyBrick();
        assertEquals(0, m.getBricks().size());

        m.buildBrick();
        m.buildBrick();

        assertEquals(1, m.getBricks().size());

        m.processKey("UP");
        m.buildBrick();
        m.processKey("DOWN");
        m.buildBrick();

        assertEquals(3, m.getBricks().size());
        m.destroyBrick();
        m.processKey("UP");
        m.destroyBrick();
        assertEquals(1, m.getBricks().size());

        for (int i = 0; i < 10 ; i++) {
            m.processKey("LEFT");
        }
        m.buildBrick();
        assertEquals(1, m.getBricks().size());
    }

    @Test
    public void TestMoveBullets() {
        hero.reload(3);
        for (int i = 0; i < 3; i++)
            map.fireBullet();
        List<Bullet> bullets = map.getBullets();
        Position a = bullets.get(0).getPosition();
        Position b = bullets.get(1).getPosition();
        Position c = bullets.get(2).getPosition();
        map.moveBullets();
        assertTrue(a.getX() - bullets.get(0).getPosition().getX() == -1);
        assertTrue(b.getX() - bullets.get(1).getPosition().getX() == -1);
        assertTrue(c.getX() - bullets.get(2).getPosition().getX() == -1);
    }

    @Test
    public void TestCollisionBulletWall() {
        map.spawnEnemies(1);
        map.getEnemies().get(0).setPosition(new Position(2,4));

        hero.setPosition(new Position(2,2));
        hero.setDirection('D');
        map.fireBullet();
        hero.setDirection('R');
        map.fireBullet();
        assertEquals(2, map.getBullets().size() );
        map.moveBullets();
        map.getEnemies().get(0).setPosition(new Position(2,3));
        map.moveBullets();
        assertEquals(1, map.getBullets().size() );
    }

    @Test
    public void TestCollisionBulletWall2() {
        hero.setPosition(new Position(2,2));
        hero.setDirection('L');
        map.fireBullet();
        map.moveBullets();
        hero.setDirection('R');
        map.fireBullet();
        map.moveBullets();
        assertEquals(1, map.getBullets().size() );
    }

    @Test
    public void TestHitSomething() {
        map.spawnEnemies(2);
        map.getEnemies().get(0).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY()));
        map.fireBullet();
        map.moveBullets();
        map.moveBullets();
        assertEquals(1, map.getEnemies().size() );

        hero.setDirection('L');
        map.getEnemies().get(0).setPosition(new Position(hero.moveLeft().getX() - 1, hero.moveLeft().getY()));
        map.fireBullet();
        map.moveBullets();
        assertEquals(1, map.getEnemies().size());

        map.moveBullets();
        assertEquals(0, map.getBullets().size() );
        assertEquals(0, map.getEnemies().size());


        assertEquals(10,map.getScore());
    }

    @Test
    public void TestFireBullets() {
        Hero h = map.getHero();
        h.reload(5);
        for (int i = 0; i < 5; i++) {
            map.fireBullet();
        }
        assertEquals(5, map.getBullets().size());

    }

    @Test
    public void TestBuildBrick() {
        Boolean up = false, down = false, right = false, left = false;
        map.buildBrick();
        hero.setDirection('L');
        map.buildBrick();
        hero.setDirection('U');
        map.buildBrick();
        hero.setDirection('D');
        map.buildBrick();
        for (Brick b : map.getBricks()) {
            if (b.getPosition().equals(hero.moveUp()))
                up = true;
            else if (b.getPosition().equals(hero.moveDown()))
                down = true;
            else if (b.getPosition().equals(hero.moveLeft()))
                left = true;
            else if (b.getPosition().equals(hero.moveRight()))
                right = true;
        }
        assertTrue(up);
        assertTrue(down);
        assertTrue(left);
        assertTrue(right);
    }

    @Test
    public void TestMoveHero() {

        Position p = hero.moveUp();
        map.moveHero(hero.moveUp(), 'U');
        assertEquals(hero.getPosition(), p);

        p = hero.moveDown();
        map.moveHero(hero.moveDown(), 'D');
        assertEquals(hero.getPosition(), p);


        p = hero.moveLeft();
        map.moveHero(hero.moveLeft(), 'L');
        assertEquals(hero.getPosition(), p);


        p = hero.moveRight();
        map.moveHero(hero.moveRight(), 'R');
        assertEquals(hero.getPosition(), p);
    }

    @Test
    public void TestMoveEnemies() {
        map.spawnEnemies(3);
        map.getEnemies().get(0).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() + 2));
        map.getEnemies().get(1).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY()));
        map.getEnemies().get(2).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() - 2));

        Position E0Pos = map.getEnemies().get(0).getPosition();
        Position E1Pos = map.getEnemies().get(1).getPosition();
        Position E2Pos = map.getEnemies().get(2).getPosition();

        map.moveHero(hero.moveRight(), 'R');
        map.moveHero(hero.moveRight(), 'R');
        map.moveHero(hero.moveRight(), 'R');
        map.moveHero(hero.moveRight(), 'R');


        map.moveEnemies();

        Position newE0Pos = map.getEnemies().get(0).getPosition();
        Position newE1Pos = map.getEnemies().get(1).getPosition();
        Position newE2Pos = map.getEnemies().get(2).getPosition();

        assertTrue(E0Pos.getX() < newE0Pos.getX() || E0Pos.getY() > newE0Pos.getY());
        assertTrue(E1Pos.getX() < newE1Pos.getX() || E1Pos.getY() == newE1Pos.getY());
        assertTrue(E2Pos.getX() < newE2Pos.getX() || E2Pos.getY() < newE2Pos.getY());

    }

    @Test
    public void TestMoveEnemiesDamage() throws Exception {
        Game game = Game.getInstance();
        game.setGameParams(60,60,"Lanterna");

        Map m = game.getMap();
        Hero h = m.getHero();
        h.reload(5);
        h.reloadBricks(5);
        h.setDirection('R');

        m.spawnEnemies(3);
        m.getEnemies().get(0).setPosition(new Position(h.moveRight().getX(), h.moveRight().getY() + 2));
        m.getEnemies().get(1).setPosition(new Position(h.moveRight().getX(), h.moveRight().getY()));
        m.getEnemies().get(2).setPosition(new Position(h.moveRight().getX(), h.moveRight().getY() - 2));


        m.moveEnemies();

        assertTrue(h.getHealth() >= 40 && h.getHealth() < 100);
    }

    @Test
    public void TestIsEnemyHere() {
        map.spawnEnemies(3);
        map.getEnemies().get(0).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() + 2));
        map.getEnemies().get(1).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY()));
        map.getEnemies().get(2).setPosition(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() - 2));


        assertTrue(map.isEnemyHere(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() + 2)));
        assertTrue(map.isEnemyHere(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY())));
        assertTrue(map.isEnemyHere(new Position(hero.moveRight().getX() + 1, hero.moveRight().getY() - 2)));

    }

    @Test
    public void TestFreeSpace() {

        map.buildBrick();
        hero.setDirection('L');
        map.buildBrick();
        hero.setDirection('U');
        map.buildBrick();
        hero.setDirection('D');

        assertTrue(!map.freeSpace(hero.moveUp()));
        assertTrue(!map.freeSpace(hero.moveLeft()));
        assertTrue(!map.freeSpace(hero.moveRight()));
        assertTrue(map.freeSpace(hero.moveDown()));
    }

    @Test
    public void TestIsHeroAlive(){
        assertTrue(map.heroStillAlive());
        hero.getsHurt(hero.getHealth()/10);
        assertTrue(map.heroStillAlive());
        hero.getsHurt(hero.getHealth());
        assertTrue(!map.heroStillAlive());
    }

    @Test
    public void TestWave(){
        assertEquals(1, map.getWave());
        assertTrue(!map.isFinishedWave());
    }

    @Test
    public void TestIsInWindow(){
        Hero herot = new Hero (new Position(0,1));
        assertTrue(!map.isInWindow(herot.getPosition()));

        herot.setPosition(new Position(2, 0));
        assertTrue(!map.isInWindow(herot.getPosition()));

        herot.setPosition(new Position(map.getWidth(), map.getHeight()-1));
        assertTrue(!map.isInWindow(herot.getPosition()));

        herot.setPosition(new Position(map.getWidth()-2, map.getHeight()));
        assertTrue(!map.isInWindow(herot.getPosition()));
    }


}
