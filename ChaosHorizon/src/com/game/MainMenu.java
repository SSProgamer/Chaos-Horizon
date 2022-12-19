package com.game;

import com.game.Game.STATE;

import java.awt.event.*;
import java.awt.*;

public class MainMenu extends MouseAdapter {
    private Game game;

    public MainMenu(Game game) {
        this.game = game;
    }

    public void render(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/logo.png");
        Image bg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/background.png");
        if (game.gameState == STATE.Menu) {
            // draw menu
            g.drawImage(bg, 0, 0, null);
            g.setFont(new Font("arial", 1, 50));
            g.setColor(Color.getHSBColor((float) 0.55, (float) 0.5, (float) 1));
            g.drawImage(img1, 150, -100, null);
            g.fillRoundRect(250, 275, 300, 60, 10, 10);
            g.fillRoundRect(250, 375, 300, 60, 10, 10);
            g.fillRoundRect(250, 475, 300, 60, 10, 10);
            g.setColor(Color.black);
            g.drawRoundRect(250, 275, 300, 60, 10, 10);
            g.drawRoundRect(250, 375, 300, 60, 10, 10);
            g.drawRoundRect(250, 475, 300, 60, 10, 10);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Start", 380, 310);
            g.drawString("Help", 380, 410);
            g.drawString("Exit", 380, 510);
            g.drawString("Highest Score", 620, 260);
            g.drawString("____________", 620, 270);
            g.drawString(": " + Game.highestScore, 620, 300);
        } else if (game.gameState == STATE.Help) {
            // draw help
            g.drawImage(bg, 0, 0, null);
            Font fnt1 = new Font("arial", 1, 48);
            Font fnt2 = new Font("arial", 1, 28);
            Font fnt3 = new Font("arial", 1, 20);

            g.setColor(new Color(255, 255, 255, 127));
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.white);
            g.fillRect(350, 160, 75, 75);
            g.fillRect(350, 260, 75, 75);
            g.fillRect(250, 260, 75, 75);
            g.fillRect(450, 260, 75, 75);
            g.fillRect(105, 400, 125, 70);

            g.setFont(fnt1);
            g.setColor(Color.black);
            g.drawString("How to play?", 245, 70);
            g.drawRect(350, 160, 75, 75);
            g.drawRect(350, 260, 75, 75);
            g.drawRect(250, 260, 75, 75);
            g.drawRect(450, 260, 75, 75);
            g.drawRect(105, 400, 125, 70);

            g.setFont(fnt2);
            g.drawString("UP", 370, 150);
            g.drawString("DOWN", 345, 370);
            g.drawString("LEFT", 250, 240);
            g.drawString("RIGHT", 450, 240);
            g.drawString("FIRE", 250, 450);
            g.drawString("W", 375, 210);
            g.drawString("A", 275, 310);
            g.drawString("S", 380, 310);
            g.drawString("D", 475, 310);
            g.drawString("SHIFT", 130, 450);

            g.setFont(fnt3);
            g.drawString("Click again for back to Menu.", 20, 550);
            g.drawString("To upgrade click \"^\"", 400, 430);
            g.drawString("which Upgrade you want to.", 400, 460);
        }
    }
}
