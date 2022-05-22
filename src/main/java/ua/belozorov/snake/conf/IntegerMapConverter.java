package ua.belozorov.snake.conf;

import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class IntegerMapConverter implements Converter<Map<Integer,Integer>> {
    @Override
    public Map<Integer,Integer> convert(Method method, String input) {
        return Arrays.stream(input.split(","))
                .map(String::strip)
                .map(kvRaw -> kvRaw.split(" +"))
                .collect(Collectors.toMap(
                        kvAsArr -> Integer.parseInt(kvAsArr[0]),
                        kvAsArr -> Integer.parseInt(kvAsArr[1]),
                        (s1, s2) -> {throw new IllegalStateException("Duplicate Map key");}
                ));
    }
}
