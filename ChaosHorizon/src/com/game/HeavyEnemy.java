package com.game;

import java.awt.*;
import java.util.*;

public class HeavyEnemy extends GameObject {
    private int startX;
    private int startY;
    private HUD hud;
    private Handler handler;
    private int HP;
    private int cooldown;
    private int endCooldown;
    private int shoot;
    private Random r;
    private int idEnemy;
    private int maxY;
    private int maxedY = 0;
    private boolean inPosition;
    private static int numberEnemy = 0;
    private Wave wave;

    public HeavyEnemy(int x, int y, ID id, Handler handler, HUD hud, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.id = id;
        this.idEnemy = idEnemy;
        numberEnemy++;
        HP = 20;
        r = new Random();
        startX = x;
        startY = y;
        inPosition = false;

        velX = 1;
        velY = 1;

        endCooldown = 200;
        cooldown = endCooldown;
    }

    public int getidEnemy() {
        return idEnemy;
    }

    public static void setNumberEnemy(int numberEnemy) {
        HeavyEnemy.numberEnemy = numberEnemy;
    }

    public void setEnemyPosition(int idEnemy) {
        if (idEnemy % 2 == 0) {
            maxY = 80;
        } else {
            maxY = 150;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }

    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, 48, 48);
    }

    public void tick() {
        if (y <= maxY) {
            maxedY += velY;
            y += velY;
        } else if (maxedY <= 210) {
            maxedY += velY;
        } else if (y >= maxY) {
            x += velX;
            inPosition = true;
        }

        shoot = r.nextInt(100);
        cooldown++;

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (x <= startX - 10 || x >= Game.WIDTH - 470 + startX - (numberEnemy * 20)) {
            velX *= -1;
        }

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 20);
            wave.setIdEnemy();
        }

        if (cooldown == endCooldown && shoot <= 5 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20, y + 48, ID.EnemyBullet, handler, 0, 3, 1));
            cooldown = 0;
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
