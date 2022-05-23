package ua.belozorov.snake.gameover;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import ua.belozorov.snake.core.NotifyingObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(fluent = true)
public class GameOver extends NotifyingObject<GameOver> {
    volatile int score;
}
