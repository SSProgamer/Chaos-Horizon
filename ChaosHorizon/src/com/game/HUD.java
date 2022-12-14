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
        g.fillRect(550, 0, 250, 600);
        g.setColor(new Color(redValue, greenValue, 0));
        g.fillRect(600, 25, Player.HEALTH * 17 / 10, 30);
        g.setColor(Color.white);
        g.drawRect(600, 25, 170, 30);

        g.drawString("HP : ", 570, 45);
        g.drawString("Wave : " + wave, 570, 90);
        g.drawString("Score : " + score, 570, 135);
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
