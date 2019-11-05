package lpoo39.Logic;

import lpoo39.Draw.DrawingGraphic;
import lpoo39.Draw.Lanterna.LanternaFactory;
import lpoo39.Draw.Swing.SwingFactory;


public class Game {

    private static long start;
    private static stateGame state;

    private static Map map;
    private static Game game_inst = null;
    private static EventLog log;
    private static GameOver gameOver;
    private static DrawingGraphic graphic;

    private Game() {

    }

    public static Game getInstance() {
        if (game_inst == null) {
            game_inst = new Game();
        }
        return game_inst;
    }

    public static stateGame getState() {
        return state;
    }

    public static DrawingGraphic getGraphic() {
        return graphic;
    }

    public static Map getMap() {
        return map;
    }

    public static GameOver getGameOver() {
        return gameOver;
    }

    public static EventLog getLog() {
        return log;
    }

    public void setGameParams(int width, int height, String graphicInterface) throws Exception {
        log = new EventLog();
        gameOver = new GameOver(new Position(width/2, height / 4));

        setFactory(graphicInterface, width, height);

        start = System.currentTimeMillis();
        state = stateGame.GAME;
    }

    private void setFactory(String graphicInterface, int width, int height) throws Exception {
        if (graphicInterface.equals("Lanterna")) {
            map = new Map(width*2, height, log);
            graphic = new LanternaFactory(width*2, height);


        } else if (graphicInterface.equals("Swing")) {
            map = new Map(width, height, log);
            graphic = new SwingFactory(width, height);


        } else
            throw new Exception("Error! Unknow graphic option.");
    }



    public void startGame() {

        while (map.heroStillAlive()) {
            log.setMessage("Wave: " + map.getWave());
            graphic.updateLog();

            map.spawnEnemies((map.getWave() + 1) * 2);
            play();
        }
        gameOver();
    }

    private void play() {
        long timer1 = System.currentTimeMillis(), timer2 = timer1;
        start = timer1;

        while (!map.isFinishedWave()) {

            if (System.currentTimeMillis() - timer2 >= 500) {
                timer2 = System.currentTimeMillis();
                map.moveEnemies();
            }

            if (System.currentTimeMillis() - timer1 >= 30) {
                timer1 = System.currentTimeMillis();
                graphic.update();
            }

            String key = graphic.getInput();
            if (key.equals("END")) {
                graphic.closeScreen();
            } else if (!key.equals(""))
                map.processKey(key);
        }
    }

    private void gameOver() {
        int score = map.getScore();
        long survivedTime = System.currentTimeMillis() - start;
        state = stateGame.GAMEOVER;

        log.setMessage("YOU DIED");
        graphic.updateLog();

        GameOver gameOver = Game.getGameOver();
        gameOver.setScore(score);
        gameOver.setSurvivedTime(survivedTime);
        graphic.update();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        graphic.closeScreen();
    }

    public enum stateGame {GAME, GAMEOVER}
}
