package com.game;

import java.awt.event.*;

import com.game.Game.STATE;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private Game game;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

        // check player movement
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // loop to all object in game
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // key events for player
                if (key == KeyEvent.VK_W && game.gameState == STATE.Game) {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S && game.gameState == STATE.Game) {
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A && game.gameState == STATE.Game) {
                    tempObject.setVelX(-5);
                    keyDown[2] = true;
                    ((Player) tempObject).playerPost(1);
                }
                if (key == KeyEvent.VK_D && game.gameState == STATE.Game) {
                    tempObject.setVelX(5);
                    keyDown[3] = true;
                    ((Player) tempObject).playerPost(2);
                }
                if (key == KeyEvent.VK_SHIFT && game.gameState == STATE.Game) {
                    ((Player) tempObject).setShoot(true);
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE && game.gameState == STATE.Game) {
            // reset game
            Player.HEALTH = 0;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // loop to all object in game
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // key events for player
                if (key == KeyEvent.VK_W && game.gameState == STATE.Game) {
                    keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S && game.gameState == STATE.Game) {
                    keyDown[1] = false;
                }
                if (key == KeyEvent.VK_A && game.gameState == STATE.Game) {
                    keyDown[2] = false;
                    ((Player) tempObject).playerPost(0);
                }
                if (key == KeyEvent.VK_D && game.gameState == STATE.Game) {
                    keyDown[3] = false;
                    ((Player) tempObject).playerPost(0);
                }
                if (key == KeyEvent.VK_SHIFT && game.gameState == STATE.Game) {
                    ((Player) tempObject).setShoot(false);
                }

                // vertical movement
                if (!keyDown[0] && !keyDown[1]) {
                    tempObject.setVelY(0);
                }
                if (!keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
