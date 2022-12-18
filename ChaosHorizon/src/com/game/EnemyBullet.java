package com.game;

import java.awt.*;

public class EnemyBullet extends GameObject {
    private Handler handler;
    private int damage;
    private Image img;

    public EnemyBullet(int x, int y, ID id, Handler handler, int velX, int velY, int damage, Image img) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.damage = damage;
        this.img = img;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    public void render(Graphics g) {
        // Graphics2D g2d = (Graphics2D) g;

        // g.setColor(Color.yellow);
        // g.fillRect(x, y, 8, 8);

        g.drawImage(img, x, y, null);

        // g.setColor(Color.green);
        // g2d.draw(getBounds());
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x >= Game.WIDTH || x <= -10 || y >= Game.HEIGHT || y <= -10) {
            handler.removeObject(this);
        }
    }

    public int getDamage() {
        return damage;
    }
}
