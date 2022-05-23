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
    private final int widthInCells;
    private final int heightInCells;

    public DrawingPanel(int dotSize, int widthInCells, int heightInCells) {
        this.canvas = new BufferedImage(
                widthInCells * dotSize, heightInCells * dotSize, BufferedImage.TYPE_INT_ARGB);
        this.dotSize = dotSize;
        this.widthInCells = widthInCells;
        this.heightInCells = heightInCells;
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
