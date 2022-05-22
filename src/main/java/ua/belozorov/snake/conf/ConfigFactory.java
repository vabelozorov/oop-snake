package ua.belozorov.snake.conf;

public class ConfigFactory {
    private static Params INSTANCE;

    public static synchronized Params getConfig() {
        if (INSTANCE == null) {
            INSTANCE = newParams();
        }
        return INSTANCE;
    }

    private static Params newParams() {
        return org.aeonbits.owner.ConfigFactory.create(Params.class);
    }
}
