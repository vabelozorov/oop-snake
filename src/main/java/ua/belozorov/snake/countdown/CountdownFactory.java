package ua.belozorov.snake.countdown;

import ua.belozorov.snake.conf.ConfigFactory;
import ua.belozorov.snake.conf.Params;

public class CountdownFactory {
    private static final CountdownFactory INSTANCE = new CountdownFactory();

    public static CountdownFactory instance() {
        return INSTANCE;
    }

    public InitialCountdown create() {
        Params config = ConfigFactory.getConfig();
        return new InitialCountdown(config.countdownDelayMs());
    }
}
