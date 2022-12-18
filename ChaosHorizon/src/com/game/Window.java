package com.game;

import javax.swing.*;

import java.awt.*;

public class Window extends Canvas {
    private JFrame frame;
    private ImageIcon img;

    public Window(int width, int height, String title, Game game) {
        img = new ImageIcon("ChaosHorizon/res/framelogo.png");
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
