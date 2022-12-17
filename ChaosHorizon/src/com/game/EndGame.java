package com.game;

import com.game.Game.STATE;

import java.awt.event.*;
import java.awt.*;

public class EndGame extends MouseAdapter {
    private Game game;

    public EndGame(Game game) {
        this.game = game;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // Retry
        if (mouseOver(mx, my, 163, 464, 72, 116)) {
            System.out.println("Retry");
            game.gameState = STATE.Game;
        }
        // Back to menu
        else if (mouseOver(mx, my, 398, 464, 72, 266)) {
            System.out.println("Menu");
            game.gameState = STATE.Menu;
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
        g.setColor(Color.white);
        Font head = new Font("arial", 1, 36);
        Font txt = new Font("arial", 1, 24);
        if (game.gameState == STATE.Lose) {
            g.setFont(head);
            g.drawString("Game End!", 100, 100);
            g.drawString("Retry", 163, 500);
            g.drawString("Back to Menu", 400, 500);
            g.setFont(txt);
            g.drawString("Score:" + "0000000", 270, 400);
        }
        if (game.gameState == STATE.Win) {
            g.setFont(head);
            g.drawString("You defeat the XD Empire.", 100, 100);
            g.drawString("Retry", 163, 500);
            g.drawString("Back to Menu", 400, 500);
            g.setFont(txt);
            g.drawString("Score:" + "0000000", 270, 400);
        }
    }
}
