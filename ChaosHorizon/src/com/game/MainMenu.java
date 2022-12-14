package com.game;
import com.game.Game.STATE;

import java.awt.event.*;
import java.awt.*;

public class MainMenu extends MouseAdapter {
    private Game game;
    private Handler handler;

    public MainMenu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (mouseOver(mx, my, 250, 275, 60, 300)) {
            game.gameState = STATE.Game;
            handler.addObject(new Player(600 / 2 - 64, game.HEIGHT - 128, ID.Player, handler));
        }
    }

    public boolean mouseOver(int mx, int my, int x, int y, int h, int w) {
        if ((x < mx && mx < x + w)) {
            if (y < my && my < y + h) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setFont(new Font("arial", 1, 50));
        g.setColor(Color.white);
        // g.drawString("God Damn Game XD!", 150, 100);
        g.setFont(new Font("arial", 1, 20));
        g.drawString("Start", 380, 310);
        Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/src/com/game/logo.png");
        g.drawImage(img1, 150, -100, null);
        g.drawRoundRect(250, 275, 300, 60, 10, 10);
        g.drawRoundRect(250, 375, 300, 60, 10, 10);
        g.drawRoundRect(250, 475, 300, 60, 10, 10);
    }

}
