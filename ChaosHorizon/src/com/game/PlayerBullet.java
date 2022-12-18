package com.game;

import java.awt.*;

public class PlayerBullet extends GameObject {
    private Handler handler;
    private int bulletDamage;

    public PlayerBullet(int x, int y, ID id, Handler handler, int velX, int velY, int bulletDamage) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.bulletDamage = bulletDamage;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 12, 24);
    }

    public void render(Graphics g) {
        Image pBulletImage = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/player/weapon/range/bullet.gif");
        g.drawImage(pBulletImage, x - 6, y, null);

        // g.setColor(Color.blue);
        // g.fillRect(x, y, 12, 24);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= -16 || y <= -8 || y >= 550) {
            handler.removeObject(this);
        }
    }

    public int getBulletDamage() {
        return bulletDamage;
    }
}
