package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.util.ScanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GamePhaseFactory {
    private static final GamePhaseFactory INSTANCE = new GamePhaseFactory();

    private final List<GamePhaseConfig> gamePhaseConfigs = new ArrayList<>();

    private GamePhases gamePhases;

    public static GamePhaseFactory instance() {
        return INSTANCE;
    }

    public List<GamePhaseConfig> gamePhaseConfigs() {
        if (gamePhaseConfigs.isEmpty()) {
            gamePhaseConfigs.addAll(ScanUtils.getAllImplementing(GamePhaseConfig.class));
        }
        return gamePhaseConfigs;
    }

    public GamePhases gamePhases() {
        if (gamePhases == null) {
            gamePhases = new GamePhases();
            gamePhaseConfigs().forEach(cfg -> gamePhases.addPhase(cfg.phase()));
        }
        return gamePhases;
    }
}
