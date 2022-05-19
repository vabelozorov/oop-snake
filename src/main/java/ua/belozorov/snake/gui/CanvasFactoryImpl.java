package ua.belozorov.snake.gui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CanvasFactoryImpl implements CanvasFactory {
    private static final CanvasFactoryImpl INSTANCE = new CanvasFactoryImpl();

    static CanvasFactoryImpl instance() {
        return INSTANCE;
    }

    @Override
    public GameCanvas gameCanvas() {
        return GuiFactory.instance().gameCanvas();
    }

}
