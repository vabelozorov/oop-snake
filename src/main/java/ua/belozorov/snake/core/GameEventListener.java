package ua.belozorov.snake.core;

public interface GameEventListener<T> {

    void modelChanged(T model);
}
