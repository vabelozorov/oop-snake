package ua.belozorov.snake.gameover;

import ua.belozorov.snake.core.GameView;
import ua.belozorov.snake.gui.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameOverView implements GameView<GameOver> {
    private final CanvasFactory canvasFactory;

    public GameOverView(CanvasFactory canvasFactory) {
        this.canvasFactory = canvasFactory;
    }

    @Override
    public void display(GameOver model) {
        GameCanvas canvas = canvasFactory.gameCanvas();

        MultilineDraw multilineDraw = new MultilineDraw(canvas, 20);
        multilineDraw.addLine("Game Over", new FontSpec("Arial", 40));
        multilineDraw.addLine("Your Score: " + model.score(), new FontSpec("Arial", 25));

        canvas.clear();

        multilineDraw.drawSpecs().forEach(line ->
                canvas.drawText(line.text(), line.drawSpec()));
    }

    private static class MultilineDraw {
        private final List<Line> lines = new ArrayList<>();
        private final GameCanvas canvas;
        private final int lineSpacing;

        private MultilineDraw(GameCanvas canvas, int lineSpacing) {
            this.canvas = canvas;
            this.lineSpacing = lineSpacing;
        }

        public void addLine(String s, FontSpec ft) {
            lines.add(new Line(s, ft, canvas.getTextBounds(s, ft)));
        }

        public Collection<Line> drawSpecs() {
            Rectangle canvasRect = canvas.getDimensions();

            long totalTextHeight = lines.stream()
                    .mapToInt(l -> l.height() + lineSpacing)
                    .sum() - lineSpacing;

            int freeSpaceAboveLines = (canvasRect.height() - (int)totalTextHeight) / 2;

            int occupiedByHigherLines = 0;

            for (Line line : lines) {

                TextDrawSpec spec = TextDrawSpec.builder()
                        .color("white")
                        .font(line.fontSpec())
                        .x((canvasRect.width() - line.width()) / 2)
                        .y(freeSpaceAboveLines + occupiedByHigherLines + line.height())
                        .build();

                occupiedByHigherLines += lineSpacing + line.height();

                line.drawSpec(spec);
            }

            return lines;
        }

        private static final class Line {
            private final String text;
            private final FontSpec fontSpec;
            private final LineLayout bounds;
            private TextDrawSpec drawSpec;

            private Line(String text, FontSpec fontSpec, LineLayout bounds) {
                this.text = text;
                this.fontSpec = fontSpec;
                this.bounds = bounds;
            }

            public String text() {
                return text;
            }

            public FontSpec fontSpec() {
                return fontSpec;
            }

            public int width() {
                return bounds.width();
            }

            public int height() {
                return bounds.ascent();
            }

            public TextDrawSpec drawSpec() {
                return drawSpec;
            }

            public void drawSpec(TextDrawSpec drawSpec) {
                this.drawSpec = drawSpec;
            }
        }

    }
}
