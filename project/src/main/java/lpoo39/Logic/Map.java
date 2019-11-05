package lpoo39.Logic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Map {

    public Hero hero;
    private int width;
    private int height;

    private int wave;
    private boolean finishedWave;

    private Background background;
    private List<Wall> walls;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Brick> bricks;
    private List<Item> collectibles;
    private List<Bullet> availableBullets;

    private EventLog log;

    public Map(int width, int height, EventLog log) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(new Position(10, 10));
        this.walls = createWalls();
        this.enemies = new ArrayList<>();//createEnemies();
        this.bullets = new ArrayList<>();
        this.bricks = new ArrayList<>();
        this.collectibles = createCollectibles();
        this.availableBullets = new ArrayList<>();
        this.background = new Background(width, height);
        this.log = log;
        this.wave = 1;
    }

    public List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            if ((c == width / 2) || (c == width / 2 + 1))
                continue;
            walls.add(new Wall(new Position(c, 1)));
            walls.add(new Wall(new Position(c, height - 1)));
        }

        for (int r = 1; r < height - 1; r++) {
            if ((r == height / 2) || (r == height / 2 + 1))
                continue;
            walls.add(new Wall(new Position(0, r)));
            walls.add(new Wall(new Position(width - 1, r)));
        }

        return walls;
    }

    private List<Item> createCollectibles() {
        Random random = new Random();
        ArrayList<Item> collectibles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Position pos = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (freeSpace(pos) && !isEnemyHere(pos))
                collectibles.add(new BulletPack(hero, pos, random.nextInt(6) + 1));
        }

        for (int i = 0; i < 5; i++) {
            Position pos = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (freeSpace(pos) && !isEnemyHere(pos))
                collectibles.add(new BrickPack(hero, pos, random.nextInt(3) + 1));
        }

        return collectibles;
    }

    public void spawnEnemies(int n) {
        Random r = new Random();
        int door = r.nextInt(4) + 1;
        Position p;


        finishedWave = false;
        if (enemies.size() < n) {
            switch (door) {
                case 1:
                    p = new Position(width / 2, 1);
                    break;
                case 2:
                    p = new Position(width / 2, height - 1);
                    break;
                case 3:
                    p = new Position(0, (height) / 2);
                    break;
                case 4:
                    p = new Position(width - 1, (height) / 2);
                    break;
                default:
                    p = new Position(width / 2, (height) / 2);
                    break;


            }
            enemies.add(new Enemy(50, 20, p));

            spawnEnemies(n);
        }

    }

    //Updates visuals
    public void draw() {

        //Background
        background.draw();

        //Bullets
        moveBullets();

        for (Bullet b : bullets)
            b.draw();

        //Walls
        for (Wall wall : walls) {
            wall.draw();
        }


        //Enemies
        for (Enemy enemy : enemies)
            enemy.draw();

        //Collectibles
        for (Item i : collectibles)
            i.draw();

        //Bricks
        for (Brick b : bricks) {
            b.draw();
        }

        hero.draw();

        if (enemies.isEmpty()) {
            finishedWave = true;
            wave++;
        }
        if (hero.getHealth() <= 0) {
            finishedWave = true;
            return;
        }
        if (collectibles.size() < 1) {
            collectibles.addAll(createCollectibles());
        }

    }

    public void processKey(String key) {
        switch (key) {
            case "DOWN":
                moveHero(hero.moveDown(), 'D');
                return;

            case "UP":
                moveHero(hero.moveUp(), 'U');
                return;

            case "LEFT":
                moveHero(hero.moveLeft(), 'L');
                return;

            case "RIGHT":
                moveHero(hero.moveRight(), 'R');
                return;

            case "BUILD":
                buildBrick();
                return;

            case "DESTROY":
                destroyBrick();
                return;

            case "SHOOT":
                fireBullet();
                return;

            default:
                break;
        }
    }

    public void destroyBrick() {
        Position p = new Position(0, 0);

        switch (hero.getDirection()) {
            case 'R':
                p = new Position(hero.getPosition().getX() + 1, hero.getPosition().getY());
                break;

            case 'L':
                p = new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
                break;

            case 'U':
                p = new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
                break;

            case 'D':
                p = new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
                break;

            default:
                log.setMessage("I don't know where to remove the brick");
                Game.getGraphic().updateLog();
                break;
        }

        for (Brick b : bricks) {
            if (b.getPosition().equals(p)) {
                bricks.remove(b);
                return;
            }
        }
        log.setMessage("There is no brick in that position!");
        Game.getGraphic().updateLog();

    }

    public void moveBullets() {
        Iterator<Bullet> i = bullets.iterator();
        while (i.hasNext()) {
            Bullet b = i.next();
            if (!hitSomething(b)) {
                Position p = b.move();
                b.setPosition(p);
                if (hitSomething(b) || !freeSpace(p) || !isInWindow(p)) {
                    availableBullets.add(b);
                    i.remove();
                }
            } else {
                availableBullets.add(b);
                i.remove();
            }
        }

    }


    public boolean hitSomething(Bullet bullet) {
        for (Enemy e : enemies) {
            if (e.getPosition().equals(bullet.getPosition())) {
                enemies.remove(e);
                hero.scored(5);
                return true;
            }
        }
        return false;
    }


    public void fireBullet() {

        if (hero.fire()) {
            Bullet b;
            if (availableBullets.isEmpty())
                b = new Bullet(hero.getPosition(), hero.getDirection());
            else {
                b = availableBullets.get(0);
                availableBullets.remove(0);
                b.setPosition(hero.getPosition());
                b.setDirection(hero.getDirection());
            }
            bullets.add(b);
        } else {
            log.setMessage("You are out of ammo!");
            Game.getGraphic().updateLog();
        }
    }


    public void buildBrick() {

        Position p = new Position(0, 0);

        switch (hero.getDirection()) {
            case 'R':
                p = new Position(hero.getPosition().getX() + 1, hero.getPosition().getY());
                break;

            case 'L':
                p = new Position(hero.getPosition().getX() - 1, hero.getPosition().getY());
                break;

            case 'U':
                p = new Position(hero.getPosition().getX(), hero.getPosition().getY() - 1);
                break;

            case 'D':
                p = new Position(hero.getPosition().getX(), hero.getPosition().getY() + 1);
                break;
            default:
                log.setMessage("I don't know where to put this wall");
                Game.getGraphic().updateLog();
                break;
        }

        if (freeSpace(p)) {
            if (hero.build()) {
                bricks.add(new Brick(p));
            } else {
                log.setMessage("You are out of bricks!");
                Game.getGraphic().updateLog();
            }
        } else {
            log.setMessage("You can't put a brick there!");
            Game.getGraphic().updateLog();
        }

    }


    public void moveHero(Position position, char direction) {
        hero.setDirection(direction);
        if (freeSpace(position) && isInWindow(position)) {
            hero.setPosition(position);
            collectiblesHere(hero.getPosition()); //Checks and handles collision with collectibles
        }
    }

    public void moveEnemies() {
        for (Enemy e : enemies) {
            enemyChase(e);
            if (e.getPosition().equals(hero.getPosition())) {
                hero.getsHurt(e.getAttack());
                log.setMessage("Enemy hit you: " + e.getAttack() + " damage");
                Game.getGraphic().updateLog();
            }

        }
    }

    //Enemies persue hero
    public void enemyChase(Enemy e) {
        Random r = new Random();
        int option = r.nextInt(2);
        switch (option) {
            case 0:
                if (e.getPosition().getY() > hero.getPosition().getY()) {
                    Position pos = e.moveUp();
                    if (freeSpace(pos) && !isEnemyHere(pos)) {
                        e.setPosition(pos);
                        e.setDirection('U');
                    }
                    return;
                } else if (e.getPosition().getY() < hero.getPosition().getY()) {
                    Position pos = e.moveDown();
                    if (freeSpace(pos) && !isEnemyHere(pos)) {
                        e.setPosition(pos);
                        e.setDirection('D');
                    }
                    return;
                }

            case 1:
                if (e.getPosition().getX() > hero.getPosition().getX()) {
                    Position pos = e.moveLeft();
                    if (freeSpace(pos) && !isEnemyHere(pos)) {
                        e.setPosition(pos);
                        e.setDirection('L');
                    }
                    return;
                } else if (e.getPosition().getX() < hero.getPosition().getX()) {
                    Position pos = e.moveRight();
                    if (freeSpace(pos) && !isEnemyHere(pos)) {
                        e.setPosition(pos);
                        e.setDirection('R');
                    }
                    return;

                }
        }

    }


    public int getWave() {
        return wave;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }


    public List<Item> getCollectibles() {
        return collectibles;
    }

    public Hero getHero() {
        return hero;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    //Checkers:

    public boolean isFinishedWave() {
        return finishedWave;
    }


    public boolean freeSpace(Position position) {
        for (Wall w : walls) {
            if (w.getPosition().equals(position)) {
                return false;
            }
        }

        for (Brick b : bricks) {
            if (b.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }

    public boolean isEnemyHere(Position position) {
        for (Enemy e : enemies) {
            if (e.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean collectiblesHere(Position position) {
        for (Item i : collectibles) {
            if (i.isHere(position)) {
                i.affectHero();
                collectibles.remove(i);

                log.setMessage(i.getDescription());
                Game.getGraphic().updateLog();
                return true;
            }
        }
        return false;

    }

    public boolean isInWindow(Position position) {
        if (position.getX() < width && position.getX() > 0) {
            return position.getY() > 0 && position.getY() < height;
        }
        return false;

    }


    public int getScore() {
        return hero.getScore();

    }

    public boolean heroStillAlive() {
        return hero.getHealth() > 0;
    }


}


