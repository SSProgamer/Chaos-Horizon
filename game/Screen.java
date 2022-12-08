package game;

import javax.swing.*;
import java.awt.*;

public class Screen {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton firButton;
    private JButton secButton;
    private JButton tirButton;

    public Screen() {
        frame = new JFrame("test");
        mainPanel = new JPanel();
        firButton = new JButton("Button 1");
        secButton = new JButton("Button 2");
        tirButton = new JButton("Button 3");

        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(firButton);
        mainPanel.add(secButton);
        mainPanel.add(tirButton);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
