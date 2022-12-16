package com.game;

import java.awt.*;

public class Boss extends GameObject {
    private HUD hud;
    private Handler handler;
    private int HP;
    private int cooldown;
    private int endCooldown;
    private boolean inPosition;

    public Boss(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.id = id;
        HP = 500;
        inPosition = true;

        velX = 3;
        velY = 3;

        endCooldown = 150;
        cooldown = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 400, 64);
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 400, 64);
    }

    public void tick() {
        cooldown++;

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 20);
        }

        if (cooldown == endCooldown && inPosition) {
            handler.addObject(new EnemyBullet(x + 20, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 11, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            cooldown = 0;
        } else if (cooldown == 100 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 5, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 6, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
        } else if (cooldown == 110 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 4, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 7, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
        } else if (cooldown == 120 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 3, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 8, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
        } else if (cooldown == 130 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 2, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 9, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
        } else if (cooldown == 140 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 1, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 10, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
        }

        collision();
    }

    private synchronized void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HP--;
                }
            }
            if (tempObject.getId() == ID.PlayerBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HP -= ((PlayerBullet) tempObject).getDamage();
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }
}
