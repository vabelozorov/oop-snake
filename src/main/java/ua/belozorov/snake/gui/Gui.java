package ua.belozorov.snake.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Gui {

    private final DrawingPanel panel;
    private final JFrame frame;
    private final SwingGameCanvas gameCanvas;

    public Gui(int dotSize, int width, int height) {
        panel = new DrawingPanel(dotSize, width, height);
        panel.setBackground(Color.black);
        panel.setFocusable(true);

        gameCanvas = new SwingGameCanvas(panel);

        frame = new JFrame();
        frame.add(panel);
    }

    public void show() {
        EventQueue.invokeLater(this::initFrame);
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
