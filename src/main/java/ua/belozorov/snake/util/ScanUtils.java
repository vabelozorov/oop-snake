package ua.belozorov.snake.util;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ScanUtils {
    static {
        ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
    }

    public static <T> List<T> getAllImplementing(Class<T> aClass) {
        try (ScanResult scanResult =
                     new ClassGraph()
                             .acceptPackages()
                             .scan()) {
            //noinspection unchecked
            return scanResult.getClassesImplementing(aClass)
                    .stream()
                    .map(ClassInfo::loadClass)
                    .map(c -> (Class<T>)c)
                    .map(ScanUtils::instantiate)
                    .collect(toList());
        }
    }

    private static <T> T instantiate(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("failed to instantiate PhaseConfig: ", e);
        }
    }
}
