package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.gui.SnakeKeyListener;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ControllerFactory {

    private static final ControllerFactory INSTANCE = new ControllerFactory();

    public static ControllerFactory instance() {
        return INSTANCE;
    }

    public SnakeKeyListener snakeKeyListener() {
        GamePhaseManager gamePhaseManager = GamePhaseFactory.instance().gamePhases();
        SnakeKeyListener snakeKeyListener = new SnakeKeyListener(gamePhaseManager);

        GamePhaseFactory.instance().gamePhaseConfigs().forEach(
                cfg -> snakeKeyListener.registerControllerForPhase(cfg.phaseId(), cfg.controller()));

        return snakeKeyListener;
    }
}
