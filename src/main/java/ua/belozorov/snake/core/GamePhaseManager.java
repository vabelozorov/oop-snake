package ua.belozorov.snake.core;

import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class GamePhaseManager {
    private final TreeSet<GamePhase> gamePhases = new TreeSet<>(comparing(GamePhase::order));
    private volatile GamePhase currentPhase;
    private volatile boolean isRunning;
    private volatile GameContext gameContext;

    public void addPhase(GamePhase phase) {
        gamePhases.add(phase);
    }

    public Class<?> currentGamePhaseId() {
        return ofNullable(currentPhase)
                .map(Object::getClass)
                .orElseThrow(() -> new RuntimeException("current Game Phase not initialized"));
    }

    public void run() throws Exception {
        isRunning = true;

        gameContext = new GameContext();

        for (GamePhase gamePhase : gamePhases) {
            if (!isRunning) break;

            currentPhase = gamePhase;
            currentPhase.run(gameContext);
            sleepMs(currentPhase.delayAfterMs());
        }
    }

    public void stop() {
        isRunning = false;
        currentPhase.stop();
        currentPhase = null;
        gameContext = null;
    }

    public void reInit() {
        gameContext = new GameContext();
        gamePhases.forEach(GamePhase::reInit);
    }
}
