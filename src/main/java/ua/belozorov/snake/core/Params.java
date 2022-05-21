package ua.belozorov.snake.core;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Accessors(fluent = true, chain = true)
@Data
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
    @Setter(AccessLevel.NONE)
    Map<Integer, Integer> snakeSpeedThresholds = new HashMap<>();

    public Params snakeSpeedThresholds(Map<Integer, Integer> snakeSpeedThresholds) {
        this.snakeSpeedThresholds.putAll(requireNonNull(snakeSpeedThresholds));
        return this;
    }

    public void load() {
        dotSize(15)
                .width(20)
                .height(20)
                .countdownDelayMs(300)
                .afterStartPhaseDelayMs(150)
                .afterInGamePhaseDelayMs(0)
                .afterGameOverPhaseDelayMs(0)
                .initialSnakeHeadPosition(Point.xy(6, 1))
                .initialSnakeTailPosition(Point.xy(1, 1))
                .snakeSpeedThresholds(Map.of(
                        10, 400,
                        15, 300,
                        20, 200,
                        25, 150
                ))
                .initialSnakeRestIntervalMs(500)
        ;
    }
}
