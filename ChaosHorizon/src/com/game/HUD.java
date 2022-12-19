package com.game;

import java.awt.*;

public class HUD {
    private int greenValue = 255;
    private int redValue = 0;

    public static int score = 0;
    private int wave = 0;

    public void tick() {
        // change color base on health
        greenValue = Player.HEALTH * 2;
        redValue = 255 - greenValue;

        greenValue = Game.clamp(greenValue, 0, 255);
        redValue = Game.clamp(redValue, 0, 255);
    }

    public void render(Graphics g) {
        // add background image
        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/sidebar.png");
        g.drawImage(img, 550, 0, null);

        g.setFont(new Font("arial", 1, 16));

        // make HP bar
        g.setColor(new Color(redValue, greenValue, 0));
        g.fillRect(600, 225, Player.HEALTH * 17 / 10, 30);
        g.setColor(Color.white);
        g.drawRect(600, 225, 170, 30);

        // hud detail
        g.drawString("Wave: " + wave, 600, 290);
        g.drawString("Score: " + score, 600, 335);
        g.drawString("Stats", 600, 400);
        g.drawString("Damage: Level " + Player.damage, 600, 430);
        g.drawString("Rate of fire: Level " + Player.raf, 600, 460);
        g.drawString("Ammo: Level " + Player.ammo, 600, 490);
        g.setColor(Color.yellow);
        g.fillRect(750, 415, 20, 20);
        g.fillRect(750, 445, 20, 20);
        g.fillRect(750, 475, 20, 20);
        g.setColor(Color.black);
        g.drawRect(750, 415, 20, 20);
        g.drawRect(750, 445, 20, 20);
        g.drawRect(750, 475, 20, 20);
        g.drawString("^", 755, 435);
        g.drawString("^", 755, 465);
        g.drawString("^", 755, 495);
        g.setColor(Color.white);
        g.setFont(new Font("arial", 1, 10));
        g.drawString("Use 50 point to upgrade.", 600, 530);
        g.drawString("(Up to Level 3)", 700, 400);
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
