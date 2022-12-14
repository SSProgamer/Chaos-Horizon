package com.game;

import java.awt.*;

public class Player extends GameObject {
    public static int HEALTH = 100;
    private Handler handler;

    private boolean shoot;
    private int cooldown;
    private int endCooldown;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        shoot = false;
        endCooldown = 30;
        cooldown = endCooldown;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 8, y + 8, 48, 48);
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);

        x += velX;
        y += velY;
        cooldown++;

        x = Game.clamp(x, 0, Game.WIDTH - 312);
        y = Game.clamp(y, 0, Game.HEIGHT - 104);
        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (shoot && (cooldown == endCooldown)) {
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler, 0, -10));
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler, 5, -10));
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler, -5, -10));
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler, 10, -10));
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler, -10, -10));

            cooldown = 0;
        }

        collision();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);
        g.fillRect(x, y, 64, 64);

        g.setColor(Color.green);
        g2d.draw(getBounds());
    }

    private synchronized void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HEALTH--;
                }
            }
            if (tempObject.getId() == ID.EnemyBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HEALTH -= ((EnemyBullet) tempObject).getDamage();
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

}
