package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.GamePhaseManager;
import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GameController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.ofNullable;

public class SnakeKeyListener extends KeyAdapter {
    private final GamePhaseManager gamePhaseManager;
    private final Map<Class<?>, GameController> phaseControllers = new HashMap<>();

    public SnakeKeyListener(GamePhaseManager gamePhaseManager) {
        this.gamePhaseManager = gamePhaseManager;
    }

    public void registerControllerForPhase(Class<? extends GamePhase> phaseClass, GameController controller) {
        phaseControllers.put(phaseClass, controller);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        Class<?> phaseClass = gamePhaseManager.currentGamePhaseId();

        GameController phaseController = requireController(phaseClass);

        switch (key) {
            case KeyEvent.VK_R -> phaseController.handleR();
            case KeyEvent.VK_SPACE -> phaseController.handleSpace();
            case KeyEvent.VK_UP -> phaseController.handleUp();
            case KeyEvent.VK_DOWN -> phaseController.handleDown();
            case KeyEvent.VK_RIGHT -> phaseController.handleRight();
            case KeyEvent.VK_LEFT -> phaseController.handleLeft();
        }
    }

    private GameController requireController(Class<?> phaseClass) {
        return ofNullable(phaseControllers.get(phaseClass))
                .orElseThrow(() -> new IllegalStateException(
                        "Cannot find a controller for the game phase " + phaseClass.getSimpleName()));
    }
}
