package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.ControllerFactory;

public class GuiFactory {
    private static final GuiFactory INSTANCE = new GuiFactory();

    public static GuiFactory instance() {
        return INSTANCE;
    }

    private Gui gui;

    private GuiFactory() {
    }

    public Gui gui() {
        if (gui == null) {
            gui = new Gui();
            SnakeKeyListener listener = ControllerFactory.instance().snakeKeyListener();
            gui.addKeyListener(listener);
        }
        return gui;
    }

    public GameCanvas gameCanvas() {
        return gui().getCanvas();
    }
}
