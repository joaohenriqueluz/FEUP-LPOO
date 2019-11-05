package lpoo39.Logic;

public class Application {
    public static void main(String[] args) {
        int width = 40;
        int height = 25;

        try {
            Game game = Game.getInstance();
            game.setGameParams(width, height, args[0]);
            game.startGame();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
