package ua.belozorov.snake.gui;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

@Builder
@Value
@Accessors(fluent = true)
public class TextDrawSpec {
    FontSpec font;
    String color;
    int x;
    int y;
}
