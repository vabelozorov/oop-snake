package ua.belozorov.snake.core;

import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;
import static ua.belozorov.snake.util.SleepUtil.sleepMs;

public class GamePhases {
    private final TreeSet<GamePhase> gamePhases = new TreeSet<>(comparing(GamePhase::order));
    private volatile GamePhase currentPhase;

    public void addPhase(GamePhase phase) {
        gamePhases.add(phase);
    }

    public Class<?> currentGamePhase() {
        return ofNullable(currentPhase)
                .map(Object::getClass)
                .orElseThrow(() -> new RuntimeException("current Game Phase not initialized"));
    }

    public void run() throws Exception {
        for (GamePhase gamePhase : gamePhases) {
            currentPhase = gamePhase;
            currentPhase.run();
            sleepMs(currentPhase.delayAfterMs());
        }
    }
}
