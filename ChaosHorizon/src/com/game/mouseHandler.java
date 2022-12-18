package com.game;

import com.game.Game.STATE;

import java.awt.event.*;

public class mouseHandler extends MouseAdapter {
    private Game game;

    public mouseHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        // play button
        if (mouseOver(mx, my, 250, 275, 60, 300) && game.gameState == STATE.Menu) {
            game.gameState = STATE.Game;
            game.playSE(6);
        }
        // help
        else if (mouseOver(mx, my, 250, 375, 60, 300) && game.gameState == STATE.Menu) {
            game.gameState = STATE.Help;
            game.playSE(6);
        }
        // back to menu from help
        else if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 0, 0, 600, 800)) {
                game.gameState = STATE.Menu;
                game.playSE(6);
            }
        }
        // exit
        else if (mouseOver(mx, my, 250, 475, 60, 300) && game.gameState == STATE.Menu) {
            if (game.gameState == STATE.Menu) {
                game.playSE(6);
                System.exit(1);
            }
        }

        // retry
        else if (mouseOver(mx, my, 163, 464, 72, 116)
                && (game.gameState == STATE.Lose || game.gameState == STATE.Win)) {
            game.gameState = STATE.Game;
            game.playSE(6);
        }
        // Back to menu
        else if (mouseOver(mx, my, 398, 464, 72, 266)
                && (game.gameState == STATE.Lose || game.gameState == STATE.Win)) {
            game.gameState = STATE.Menu;
            game.playSE(6);
        }

        // upgrade
        else if (mouseOver(mx, my, 750, 415, 20, 20) && game.gameState == STATE.Game && Player.damage <= 2
                && HUD.score >= 50) {
            // do something
            Player.damage++;
            HUD.score -= 50;
            game.playSE(6);
        } else if (mouseOver(mx, my, 750, 445, 20, 20) && game.gameState == STATE.Game && Player.raf <= 2
                && HUD.score >= 50) {
            // do something
            Player.raf++;
            HUD.score -= 50;
            game.playSE(6);
        } else if (mouseOver(mx, my, 750, 475, 20, 20) && game.gameState == STATE.Game && Player.ammo <= 2
                && HUD.score >= 50) {
            // do something
            Player.ammo++;
            HUD.score -= 50;
            game.playSE(6);
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
}
