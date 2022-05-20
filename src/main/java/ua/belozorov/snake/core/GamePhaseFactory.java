package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.util.ScanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GamePhaseFactory {
    private static final GamePhaseFactory INSTANCE = new GamePhaseFactory();

    public static GamePhaseFactory instance() {
        return INSTANCE;
    }

    private final List<GamePhaseConfig> gamePhaseConfigs = new ArrayList<>();

    private GamePhaseManager gamePhaseManager;

    public synchronized List<GamePhaseConfig> gamePhaseConfigs() {
        if (gamePhaseConfigs.isEmpty()) {
            gamePhaseConfigs.addAll(ScanUtils.getAllImplementing(GamePhaseConfig.class));
        }
        return new ArrayList<>(gamePhaseConfigs);
    }

    public synchronized GamePhaseManager gamePhases() {
        if (gamePhaseManager == null) {
            gamePhaseManager = new GamePhaseManager();
            gamePhaseConfigs().forEach(cfg -> gamePhaseManager.addPhase(cfg.phase()));
        }
        return gamePhaseManager;
    }
}
