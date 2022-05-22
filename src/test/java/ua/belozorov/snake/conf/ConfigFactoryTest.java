package ua.belozorov.snake.conf;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigFactoryTest {

    @Test
    void loadInt() {
        Params params = ConfigFactory.create(Params.class);

        assertEquals(15, params.dotSize());
    }

    @Test
    void loadMap() {
        Params params = ConfigFactory.create(Params.class);

        assertEquals(Map.of(
                10, 400,
                15, 300,
                20, 200,
                25, 150
        ), params.snakeSpeedThresholds());
    }

    @Test
    void loadPoint() {
        Params params = ConfigFactory.create(Params.class);

        assertEquals(Point.xy(6, 1), params.initialSnakeHeadPosition());
    }

    @Test
    void envValueSupersedes() {
        System.setProperty("dotSize", "1000");

        Params params = ConfigFactory.create(Params.class);

        assertEquals(1000, params.dotSize());

        System.clearProperty("dotSize");
    }
}