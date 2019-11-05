package lpoo39.Draw.Swing;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.DrawingGraphic;
import lpoo39.Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SwingFactory implements DrawingGraphic {
    private JFrame mainFrame;
    private Graphics graphics;
    private GamePanel panel;

    private JFrame logFrame;
    private Graphics logGraphics;
    private LogPanel logPanel;

    private String key;
    private int ratio,width;

    //Drawing Element objects
    private HeroSwing heroDE;
    private EnemySwing enemyDE;
    private UniObjectSwing bulletDE;
    private GameOverSwing gameOverDE;
    private UniObjectSwing brickDE;
    private BackgroundSwing backgroundDE;
    private UniObjectSwing bulletPackDE;
    private UniObjectSwing brickPackDE;
    private UniObjectSwing wallDE;
    private EventLogSwing eventLogDE;

    public SwingFactory(int width, int height) {
        ratio = 25;
        this.width = width;

        mainFrame = new JFrame("Look, a monster! PEW PEW!");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(width * ratio + 16, height * ratio + 39));
        mainFrame.setResizable(true);

        loadImagesToDEs();

        panel = new GamePanel(this);
        mainFrame.getContentPane().add(panel);

        mainFrame.pack();
        mainFrame.setVisible(true);

        createLogFrame(30, 20);

        key = "";
        setKeyListener();
    }

    private void createLogFrame(int width, int height) {
        logFrame = new JFrame("What's happening?");
        logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logFrame.setPreferredSize(new Dimension(width * 25, height * 25));
        logFrame.setResizable(true);

        logPanel = new LogPanel(this);
        logFrame.getContentPane().add(logPanel);

        logFrame.pack();
        logFrame.setVisible(true);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics g) {
        this.graphics = g;
    }

    public Graphics getLogGraphics() {
        return logGraphics;
    }

    public void setLogGraphics(Graphics logGraphics) {
        this.logGraphics = logGraphics;
    }

    public int getRatio() {
        return ratio;
    }

    public String getInput() {
        String input = key;
        if (key != "") {
            key = "";
        }
        return input;
    }

    public void loadImagesToDEs() {
        Image background = new ImageIcon("src/main/resources/arena2.png").getImage();
        Image bulletImage = new ImageIcon("src/main/resources/bullet.png").getImage();
        Image otherImage = new ImageIcon("src/main/resources/bullet.png").getImage();
        Image gameOverImage = new ImageIcon("src/main/resources/gameOver.png").getImage();

        Image heroUp = new ImageIcon("src/main/resources/playerUp.png").getImage();
        Image heroDown = new ImageIcon("src/main/resources/playerDown.png").getImage();
        Image heroRight = new ImageIcon("src/main/resources/playerRight.png").getImage();
        Image heroLeft = new ImageIcon("src/main/resources/playerLeft.png").getImage();

        Image enemyUp = new ImageIcon("src/main/resources/monsterUp.png").getImage();
        Image enemyDown = new ImageIcon("src/main/resources/monsterDown.png").getImage();
        Image enemyRight = new ImageIcon("src/main/resources/monsterRight.png").getImage();
        Image enemyLeft = new ImageIcon("src/main/resources/monsterLeft.png").getImage();

        Image wallImage = new ImageIcon("src/main/resources/wallImage.png").getImage();
        Image brickImage = new ImageIcon("src/main/resources/box.png").getImage();
        Image brickBlockImage = new ImageIcon("src/main/resources/brickblock.png").getImage();


        backgroundDE = new BackgroundSwing();
        backgroundDE.setImage(background);

        enemyDE = new EnemySwing();
        enemyDE = new EnemySwing();
        enemyDE.setLookUp(enemyUp);
        enemyDE.setLookDown(enemyDown);
        enemyDE.setLookLeft(enemyLeft);
        enemyDE.setLookRight(enemyRight);

        heroDE = new HeroSwing();
        heroDE.setLookUp(heroUp);
        heroDE.setLookDown(heroDown);
        heroDE.setLookLeft(heroLeft);
        heroDE.setLookRight(heroRight);

        wallDE = new UniObjectSwing();
        wallDE.setImage(wallImage);

        bulletDE = new UniObjectSwing();
        bulletDE.setImage(bulletImage);

        gameOverDE = new GameOverSwing();
        gameOverDE.setImage(gameOverImage);

        brickPackDE = new UniObjectSwing();
        brickPackDE.setImage(otherImage);

        bulletPackDE = new UniObjectSwing();
        bulletPackDE.setImage(brickBlockImage);

        brickDE = new UniObjectSwing();
        brickDE.setImage(brickImage);

        eventLogDE = new EventLogSwing();
    }


    public void setKeyListener() {

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                key = getKey(e);
                return false;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
    }

    private String getKey(KeyEvent e) {

        if (e.getID() == KeyEvent.KEY_TYPED) {
            switch (e.getKeyChar()) {
                case 'b':
                    return "BUILD";
                case 'd':
                    return "DESTROY";
                case ' ':
                    return "SHOOT";
                default:
                    return "";
            }
        } else if (e.getID() == KeyEvent.KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case 27:
                    return "END";
                case 39:
                    return "RIGHT";
                case 38:
                    return "UP";
                case 40:
                    return "DOWN";
                case 37:
                    return "LEFT";
                default:
                    return "";
            }
        } else {
            return "";
        }
    }

    @Override
    public DrawingElement getDrawingObject(Element element) {
        if (element.getClass().equals(Hero.class)) {
            return heroDE;
        }
        if (element.getClass().equals(GameOver.class)) {
            return gameOverDE;
        }
        if (element.getClass().equals(Background.class)) {
            return backgroundDE;
        }
        if (element.getClass().equals(EventLog.class)) {
            return eventLogDE;
        }
        if (element.getClass().equals(Brick.class)) {
            return brickDE;
        }
        if (element.getClass().equals(BrickPack.class)) {
            return brickPackDE;
        }
        if (element.getClass().equals(Bullet.class)) {
            return bulletDE;
        }
        if (element.getClass().equals(BulletPack.class)) {
            return bulletPackDE;
        }
        if (element.getClass().equals(Enemy.class)) {
            return enemyDE;
        }
        if (element.getClass().equals(Wall.class)) {
            return wallDE;
        }
        return null;
    }

    @Override
    public void update() {
        panel.repaint();
    }

    public void draw() {
        Map map = Game.getMap();
        if (Game.getState() == Game.stateGame.GAME) {
            map.draw();
        } else {
            GameOver gameOver = Game.getGameOver();
            map.draw();
            gameOver.draw();
        }
    }

    @Override
    public void updateLog() {
        logPanel.repaint();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void logDraw() {
        Game.getLog().draw();
    }

    @Override
    public void closeScreen() {
        System.exit(0);
    }

}
