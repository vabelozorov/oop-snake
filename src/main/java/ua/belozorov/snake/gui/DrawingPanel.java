package ua.belozorov.snake.gui;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Accessors(fluent = true)
@Getter
class DrawingPanel extends JPanel {
    private final BufferedImage canvas;
    private final int dotSize;
    private final int width;
    private final int height;

    public DrawingPanel(int dotSize, int width, int height) {
        this.canvas = new BufferedImage(
                width * dotSize, height * dotSize, BufferedImage.TYPE_INT_ARGB);
        this.dotSize = dotSize;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
}
