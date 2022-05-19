package ua.belozorov.snake.gui;

public interface CanvasFactory {
    static CanvasFactory instance() {
        return CanvasFactoryImpl.instance();
    }

    GameCanvas gameCanvas();
}
