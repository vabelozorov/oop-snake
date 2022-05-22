package ua.belozorov.snake.conf;

import org.aeonbits.owner.Converter;
import ua.belozorov.snake.core.Point;

import java.lang.reflect.Method;

public class PointConverter implements Converter<Point> {
    @Override
    public Point convert(Method method, String input) {
        String[] xyRaw = input.strip().split(" +");
        return Point.xy(Integer.parseInt(xyRaw[0]), Integer.parseInt(xyRaw[1]));
    }
}
