package ua.belozorov.snake.gui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ua.belozorov.snake.conf.ConfigFactory;
import ua.belozorov.snake.conf.Params;
import ua.belozorov.snake.core.ControllerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GuiFactory {
    private static final GuiFactory INSTANCE = new GuiFactory();

    public static GuiFactory instance() {
        return INSTANCE;
    }

    private Gui gui;

    public synchronized Gui gui() {
        if (gui == null) {
            Params params = ConfigFactory.getConfig();
            gui = new Gui(params.dotSize(), params.width(), params.height());
            SnakeKeyListener listener = ControllerFactory.instance().snakeKeyListener();
            gui.addKeyListener(listener);
        }
        return gui;
    }

    public GameCanvas gameCanvas() {
        return gui().getCanvas();
    }
}
