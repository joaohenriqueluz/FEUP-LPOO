package lpoo39.Draw.Lanterna;

import lpoo39.Draw.DrawingElement;
import lpoo39.Draw.DrawingGraphic;
import lpoo39.Logic.*;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaFactory implements DrawingGraphic {
    private static TerminalScreen screen;
    private static TerminalScreen logScreen;
    private int width;

    public LanternaFactory(int width, int height) {
        this.width = width;
        try {
            //Main Screen
            startMainScreen(width, height);

            //EventLog Screen
            startEventLogScreen(50, 20);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startMainScreen(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    private void startEventLogScreen(int width, int height) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        logScreen = new TerminalScreen(terminal);

        logScreen.setCursorPosition(null);   // we don't need a cursor
        logScreen.startScreen();             // screens must be started
        logScreen.doResizeIfNecessary();     // resize screen if necessary
    }

    public String getInput() {
        KeyStroke key = null;
        try {
            key = screen.pollInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (key == null)
            return "";

        if (key != null && (key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.EOF)) {
            return "END";
        }

        switch (key.getKeyType()) {
            case ArrowDown:
                return "DOWN";

            case ArrowUp:
                return "UP";

            case ArrowLeft:
                return "LEFT";

            case ArrowRight:
                return "RIGHT";

        }

        if (key.getCharacter() == 'b') { //Build lpoo39.Logic.Wall
            return "BUILD";
        } else if (key.getCharacter() == 'd') { //Destroy lpoo39.Logic.Brick
            return "DESTROY";
        } else if (key.getCharacter() == ' ') {
            return "SHOOT";
        } else {
            return "";
        }
    }


    public DrawingElement getDrawingObject(Element element) {
        if (element.getClass().equals(Hero.class)) {
            return new HeroLanterna(screen);
        }
        if (element.getClass().equals(GameOver.class)) {
            return new GameOverLanterna(screen);
        }
        if (element.getClass().equals(Background.class)) {
            return new BackgroundLanterna(screen);
        }
        if (element.getClass().equals(EventLog.class)) {
            return new EventLogLanterna(logScreen);
        } else
            return new UniObjectLanterna(screen);

    }

    @Override
    public void update() {
        draw();
    }

    public void draw() {
        if (Game.getState() == Game.stateGame.GAME) {
            Map map = Game.getMap();
            map.draw();
        } else {
            GameOver gameOver = Game.getGameOver();
            gameOver.draw();
        }
    }

    @Override
    public void updateLog() {
        Game.getLog().draw();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void closeScreen() {
        try {
            screen.close();
            logScreen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
