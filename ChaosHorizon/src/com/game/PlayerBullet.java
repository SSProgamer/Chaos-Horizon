package com.game;

import java.awt.*;

public class PlayerBullet extends GameObject {
    private Handler handler;
    private int damage;

    public PlayerBullet(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        damage = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 16);
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 8, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= -16 || y <= -8 || y >= 550) {
            handler.removeObject(this);
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}