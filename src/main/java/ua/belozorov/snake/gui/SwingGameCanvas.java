package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.Point;

import java.awt.*;

class SwingGameCanvas implements GameCanvas {

    private final DrawingPanel drawingPanel;
    private final FontMetrics fontMetrics;
    private final Font font;

    public SwingGameCanvas(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        font = new Font("Helvetica", Font.BOLD, 60);
        fontMetrics = drawingPanel.getFontMetrics(font);
    }

    @Override
    public void drawCenteredText(String s) {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(
                s,
                (drawingPanel.getWidth() - fontMetrics.stringWidth(s)) / 2,
                (drawingPanel.getHeight()) / 2
        );
        drawingPanel.repaint();
    }

    @Override
    public void clear() {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.clearRect(0,0, drawingPanel.getWidth(), drawingPanel.getHeight());
    }

    @Override
    public void drawSnakeSegment(Point segment) {
        Graphics2D g = drawingPanel.canvas().createGraphics();
        g.setColor(Color.RED);
        g.fillOval(
                segment.x() * drawingPanel.dotSize(),
                (drawingPanel.height() - 1 - segment.y()) * drawingPanel.dotSize(),
                drawingPanel.dotSize(),
                drawingPanel.dotSize()
        );
        drawingPanel.repaint();
    }
}
