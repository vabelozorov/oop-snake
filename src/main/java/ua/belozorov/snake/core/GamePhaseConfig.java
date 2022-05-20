package ua.belozorov.snake.core;

public interface GamePhaseConfig {
    GamePhase phase();

    GamePhaseController controller();

    Class<? extends GamePhase> phaseId();
}
