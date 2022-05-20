package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameFactory {
    private static final GameFactory INSTANCE = new GameFactory();
    private Game game;

    public static GameFactory instance() {
        return INSTANCE;
    }

    public Game game() {
        GamePhaseManager gamePhaseManager = GamePhaseFactory.instance().gamePhases();
        synchronized (this) {
            if (game == null) {
                game = new Game(gamePhaseManager);
            }
        }
        return game;
    }
}
