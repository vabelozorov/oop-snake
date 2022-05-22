package ua.belozorov.snake.conf;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import ua.belozorov.snake.core.Point;

import java.util.Map;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties",
        "classpath:app.conf"
})
public interface Params extends Config {
    int dotSize();

    int width();

    int height();

    int countdownDelayMs();

    int afterStartPhaseDelayMs();

    int afterInGamePhaseDelayMs();

    int afterGameOverPhaseDelayMs();

    int initialSnakeRestIntervalMs();

    @ConverterClass(PointConverter.class)
    Point initialSnakeHeadPosition();

    @ConverterClass(PointConverter.class)
    Point initialSnakeTailPosition();

    @ConverterClass(IntegerMapConverter.class)
    Map<Integer, Integer> snakeSpeedThresholds();
}
