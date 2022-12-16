package com.game;

import java.awt.*;
import java.util.*;

public class FastEnemy extends GameObject {
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

    public FastEnemy(int x, int y, ID id, Handler handler, HUD hud, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.id = id;
        this.idEnemy = idEnemy;
        numberEnemy++;
        HP = 10;
        r = new Random();
        startX = x;
        startY = y;
        inPosition = true;
        velX = 5;
        velY = 5;

        endCooldown = 75;
        cooldown = endCooldown;
    }

    public int getidEnemy() {
        return idEnemy;
    }

    public static void setNumberEnemy(int numberEnemy) {
        FastEnemy.numberEnemy = numberEnemy;
    }

    public void setEnemyPosition(int idEnemy) {
        if (idEnemy % 2 == 0) {
            maxY = 80;
        } else {
            maxY = 150;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void render(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/small_ship/small_enemy_16px.gif");
        g.drawImage(img1, x, y, null);
        // g.setColor(Color.pink);
        // g.fillRect(x, y, 16, 16);
    }

    public void tick() {
        if (x <= 0) {
            // y -= velY;
            x += velX;
        } else {
            y += velY;
            x += velX;
        }

        shoot = r.nextInt(100);
        cooldown++;

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (x <= numberEnemy * -45 || x >= Game.WIDTH) {
            velX *= -1;
        }
        if (y <= 100 || y >= 300) {
            velY *= -1;
        }

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 10);
            wave.setIdEnemy();
        }

        if (cooldown == endCooldown && shoot <= 5 && inPosition) {
            handler.addObject(new EnemyBullet(x + 4, y + 16, ID.EnemyBullet, handler, 0, 7, 1));
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
