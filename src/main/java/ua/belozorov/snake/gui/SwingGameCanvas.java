package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.Point;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

class SwingGameCanvas implements GameCanvas {

    private final DrawingPanel drawingPanel;
    private final Font defaultFont;

    public SwingGameCanvas(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;

        defaultFont = new Font("Helvetica", Font.BOLD, 60);
    }

    @Override
    public void drawCenteredText(String s) {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.setColor(Color.white);
        g.setFont(defaultFont);
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout layout = new TextLayout(s, defaultFont, frc);
        Rectangle2D textBounds = layout.getBounds();
        g.drawString(
                s,
                (drawingPanel.getWidth() - (int) textBounds.getWidth()) / 2,
                (drawingPanel.getHeight()) / 2
        );
        drawingPanel.repaint();
    }

    @Override
    public void drawText(String s, TextDrawSpec spec) {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.getColor(spec.color()));

        FontSpec fontSpec = spec.font();
        g.setFont(new Font(fontSpec.font(), Font.BOLD, fontSpec.fontSize()));

        g.drawString(s, spec.x(), spec.y());
        drawingPanel.repaint();
    }

    @Override
    public void clear() {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.clearRect(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight());
    }

    @Override
    public void drawSnakeSegment(Point segment) {
        Graphics2D g = drawingPanel.canvas().createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.RED);
        g.fillOval(
                segment.x() * drawingPanel.dotSize(),
                (drawingPanel.heightInCells() - 1 - segment.y()) * drawingPanel.dotSize(),
                drawingPanel.dotSize(),
                drawingPanel.dotSize()
        );
        drawingPanel.repaint();
    }

    @Override
    public void drawApple(Point point) {
        Graphics2D g = drawingPanel.canvas().createGraphics();
        g.setColor(Color.GREEN);
        g.fillOval(
                point.x() * drawingPanel.dotSize(),
                (drawingPanel.heightInCells() - 1 - point.y()) * drawingPanel.dotSize(),
                drawingPanel.dotSize(),
                drawingPanel.dotSize()
        );
        drawingPanel.repaint();
    }

    @Override
    public LineLayout getTextBounds(String s, FontSpec fontSpec) {
        Graphics2D g = drawingPanel.canvas().createGraphics();
        FontRenderContext frc = g.getFontRenderContext();
        TextLayout layout = new TextLayout(s, new Font(fontSpec.font(), Font.BOLD, fontSpec.fontSize()), frc);
        return new LineLayout(
                (int) layout.getBounds().getWidth(),
                (int) layout.getAscent(),
                (int) layout.getDescent()
        );
    }

    @Override
    public Rectangle getDimensions() {
        return new Rectangle(drawingPanel.getWidth(), drawingPanel.getHeight());
    }

}
