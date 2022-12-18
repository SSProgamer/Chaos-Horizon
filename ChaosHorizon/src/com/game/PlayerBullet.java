package com.game;

import java.awt.*;

public class PlayerBullet extends GameObject {
    private Handler handler;
    // Damage of out bullet | 1 -> 2 -> 3
    public static int damage;

    public PlayerBullet(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        damage = 3;
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
}
