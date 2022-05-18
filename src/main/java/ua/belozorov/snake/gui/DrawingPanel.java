package ua.belozorov.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class DrawingPanel extends JPanel {
    private final BufferedImage canvas;

    public DrawingPanel(BufferedImage canvas) {
        this.canvas = canvas;
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

    public BufferedImage getCanvas() {
        return canvas;
    }
}
