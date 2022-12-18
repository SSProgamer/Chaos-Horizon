package com.game;

import com.game.Game.STATE;

import java.awt.event.*;
import java.awt.*;

public class EndGame extends MouseAdapter {
    private Game game;

    public EndGame(Game game) {
        this.game = game;

    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        Font head = new Font("arial", 1, 36);
        Font txt = new Font("arial", 1, 24);
        Image loseImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/losebg.png");
        Image winImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/winbg.png");

        if (game.gameState == STATE.Lose) {
            g.drawImage(loseImg, 0, 0, null);
            g.setFont(head);
            g.drawString("You are crash and become Mermaid", 100, 100);
            g.drawString("Retry", 163, 500);
            g.drawString("Back to Menu", 400, 500);
            g.setFont(txt);
            g.drawString("Score:" + "0000000", 270, 400);
        }
        if (game.gameState == STATE.Win) {
            g.drawImage(winImg, 0, 0, null);
            g.setFont(head);
            g.drawString("You defeat the XD Empire.", 100, 100);
            g.drawString("Retry", 163, 500);
            g.drawString("Back to Menu", 400, 500);
            g.setFont(txt);
            g.drawString("Score:" + "0000000", 270, 400);
        }
    }
}
