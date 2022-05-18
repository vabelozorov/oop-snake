package ua.belozorov.snake.core;

public interface GamePhaseConfig {
    GamePhase phase();

    GameController controller();

    Class<? extends GamePhase> phaseId();
}
