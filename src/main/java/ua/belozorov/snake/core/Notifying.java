package ua.belozorov.snake.core;

import java.util.Collection;

public interface Notifying<T> {
    void addListener(GameEventListener<T> listener);

    void notifyListeners();

    Collection<GameEventListener<T>> listeners();
}
