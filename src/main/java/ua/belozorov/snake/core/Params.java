package ua.belozorov.snake.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
@Getter
@Setter
public class Params {
    private static Params INSTANCE;

    public static synchronized Params instance() {
        if (INSTANCE == null) {
            INSTANCE = new Params();
            INSTANCE.load();
        }
        return INSTANCE;
    }

    int dotSize;
    int width;
    int height;
    int countdownDelayMs;
    int afterStartPhaseDelayMs;
    int afterInGamePhaseDelayMs;
    int afterGameOverPhaseDelayMs;
    int initialSnakeRestIntervalMs;
    Point initialSnakeHeadPosition;
    Point initialSnakeTailPosition;

    public void load() {
        dotSize(15)
        .width(25)
        .height(25)
        .countdownDelayMs(300)
        .afterStartPhaseDelayMs(150)
        .afterInGamePhaseDelayMs(0)
        .afterGameOverPhaseDelayMs(0)
        .initialSnakeRestIntervalMs(400)
        .initialSnakeHeadPosition(Point.xy(6, 1))
        .initialSnakeTailPosition(Point.xy(1, 1));
    }
}
