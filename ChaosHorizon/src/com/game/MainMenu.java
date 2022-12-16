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
        
        //play button
        if (mouseOver(mx, my, 250, 275, 60, 300)) {
            game.gameState = STATE.Game;
            handler.addObject(new Player(600 / 2 - 64, Game.HEIGHT - 128, ID.Player, handler));
        }
        //help
        else if (mouseOver(mx, my, 250, 375, 60, 300)){
            System.out.println("yeee");
            game.gameState = STATE.Help;
        }
        //back to menu from help
        else if (game.gameState == STATE.Help){
            if(mouseOver(mx, my, 0, 0, 600, 800))
            game.gameState = STATE.Menu;
        }
        //exit
        else if (mouseOver(mx, my, 250,475,60,300)){
            System.exit(1);
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
        if (game.gameState == STATE.Menu) {
            g.setFont(new Font("arial", 1, 50));
            g.setColor(Color.white);
            // g.drawString("God Damn Game XD!", 150, 100);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Start", 380, 310);
            g.drawString("Help", 380, 410);
            g.drawString("Exit", 380, 510);
            
            Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/src/com/game/logo.png");
            g.drawImage(img1, 150, -100, null);
            g.drawRoundRect(250, 275, 300, 60, 10, 10);
            g.drawRoundRect(250, 375, 300, 60, 10, 10);
            g.drawRoundRect(250, 475, 300, 60, 10, 10);
        }else if(game.gameState == STATE.Help){
            //drawhelp
            Font fnt1 = new Font("arial",1,48);
            Font fnt2 = new Font("arial",1,28);
            Font fnt3 = new Font("arial", 1,20); 
            g.setFont(fnt1);
            g.setColor(Color.white);
            g.drawString("How to play?",45,70);
            g.drawRect(350, 160, 75, 75);
            g.drawRect(350, 260, 75, 75);
            g.drawRect(250, 260, 75, 75);
            g.drawRect(450, 260, 75, 75);
            g.drawRect(105, 400, 125, 70);
            g.setFont(fnt2);
            g.drawString("UP", 370, 150);
            g.drawString("DOWN", 345, 370);
            g.drawString("LEFT", 250, 240);
            g.drawString("RIGHT",450,240);
            g.drawString("FIRE", 250,450);
            g.drawString("W",375,210);
            g.drawString("A",275,310);
            g.drawString("S",380,310);
            g.drawString("D",475,310);
            g.drawString("SHIFT",130,450);
            g.setFont(fnt3);
            g.drawString("Click again for back to Menu.", 20, 550);
            g.drawString("To upgrade click \"Upgrade\"", 400, 430);
            g.drawString("and Click which Upgrage you want to.", 400, 460);
            
        }
    }

}
