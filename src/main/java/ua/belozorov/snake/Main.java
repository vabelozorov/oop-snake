package ua.belozorov.snake;

import ua.belozorov.snake.core.*;
import ua.belozorov.snake.gui.GuiFactory;

public class Main {

    public static void main(String[] args) {
        Params.load();

        GameFactory.instance()
                .game().startNew();

        GuiFactory.instance()
                .gui().show();
    }
}
