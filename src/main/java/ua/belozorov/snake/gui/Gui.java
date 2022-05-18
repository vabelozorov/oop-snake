package ua.belozorov.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Gui {

    private final DrawingPanel panel;
    private final JFrame frame;
    private final SwingGameCanvas gameCanvas;

    public Gui() {
        BufferedImage canvas = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        panel = new DrawingPanel(canvas);

        gameCanvas = new SwingGameCanvas(panel);

        frame = new JFrame();
        frame.add(panel);
    }

    public void show() {
        EventQueue.invokeLater( () -> {
            initPanel();
            initFrame();
        });
    }

    private void initPanel() {
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.black);
        panel.setFocusable(true);
    }

    private void initFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public GameCanvas getCanvas() {
        return gameCanvas;
    }

    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

}
