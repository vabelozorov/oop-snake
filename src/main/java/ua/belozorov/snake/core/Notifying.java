package ua.belozorov.snake.core;

public interface Notifying<T> {
    void addListener(GameEventListener<T> listener);

    void notifyListeners();
}
