package com.game;

import java.awt.*;

public class HUD {
    private int greenValue = 255;
    private int redValue = 0;

    private int score = 0;
    private int wave = 0;

    public void tick() {
        greenValue = Player.HEALTH * 2;
        redValue = 255 - greenValue;

        greenValue = Game.clamp(greenValue, 0, 255);
        redValue = Game.clamp(redValue, 0, 255);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.setFont(new Font("arial",1,16));
        g.fillRect(550, 0, 250, 600);
        g.setColor(new Color(redValue, greenValue, 0));
        g.fillRect(600, 125, Player.HEALTH * 17 / 10, 30);
        g.setColor(Color.white);
        g.drawRect(600, 125, 170, 30);
        
        g.drawString("HP : ", 570, 145);
        g.drawString("Wave : " + wave, 570, 190);
        g.drawString("Score : " + score, 570, 235);
        g.drawString("Stats",570,300);
        g.drawString("DMG",570, 330);
        g.drawString("MAG",570, 360);
        g.drawString("FIRE",570, 390);
        g.drawString("BOMB",570, 450);
        g.drawString("DRILL",680, 450);
        g.drawRect(750, 315, 20, 20);
        g.drawRect(750, 345, 20, 20);
        g.drawRect(750, 375, 20, 20);
        g.drawString("^", 755, 335);
        g.drawString("^", 755, 365);
        g.drawString("^", 755, 395);
        
        // g.setFont(new Font("arial",1,24));
        // g.drawString("UPGRADE",600,500);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
