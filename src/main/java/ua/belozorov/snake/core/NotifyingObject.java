package ua.belozorov.snake.core;

import java.util.ArrayList;
import java.util.List;

public abstract class NotifyingObject<T> implements Notifying<T> {
    private final List<GameEventListener<T>> listeners = new ArrayList<>();

    @Override
    public void addListener(GameEventListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyListeners() {
        //noinspection unchecked
        listeners.forEach(l -> l.modelChanged((T) this));
    }
}
