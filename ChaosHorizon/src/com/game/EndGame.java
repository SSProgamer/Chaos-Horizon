package com.game;
import com.game.Game.STATE;

import java.awt.event.*;
import java.awt.*;
public class EndGame extends MouseAdapter{
    private Handler handler;
    private Game game;
    public EndGame(Game game,Handler handler){
        this.handler = handler;
        this.game = game;
        

    }
    
    public void tick() {

    }

    public void render(Graphics g){
        if(game.gameState == STATE.Lose){
            g.drawString("Game End!",100,100);
        }
        if(game.gameState == STATE.Win){
            g.drawString("You defeat the XD Empire.", 100, 100);
        }
    }
}
