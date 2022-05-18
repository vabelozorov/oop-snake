package ua.belozorov.snake;

import ua.belozorov.snake.core.*;
import ua.belozorov.snake.gui.GuiFactory;

public class Main {

    public static void main(String[] args) {

        GamePhases gamePhases = GamePhaseFactory.instance().gamePhases();

        new GameLoop(gamePhases)
                .start();

        GuiFactory.instance()
                .gui().show();
    }
}
