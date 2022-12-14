package com.game;

import java.awt.*;
import java.util.*;

public class BasicEnemy extends GameObject {
    private int startX;
    private Handler handler;
    private PlaySound playSound;

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
    private Image bulletImg;

    public BasicEnemy(int x, int y, ID id, Handler handler, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.idEnemy = idEnemy;
        numberEnemy++;
        HP = 10;
        r = new Random();
        startX = x;
        inPosition = false;
        playSound = new PlaySound();
        bulletImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/medium_ship/MediumBullet.gif");

        velX = 3;
        velY = 3;

        endCooldown = 100;
        cooldown = endCooldown;
    }

    public int getidEnemy() {
        return idEnemy;
    }

    public static void setNumberEnemy(int numberEnemy) {
        BasicEnemy.numberEnemy = numberEnemy;
    }

    public void setEnemyPosition(int idEnemy) {
        if (idEnemy % 2 == 0) {
            maxY = 150;
            maxedY = -280;
        } else {
            maxY = 220;
            maxedY = -280;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void render(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/medium_ship/enemy_medium.gif");
        g.drawImage(img, x, y, null);
    }

    public void tick() {
        // go to position
        if (y <= maxY) {
            maxedY += velY;
            y += velY;
        } else if (maxedY <= 0) {
            maxedY += velY;
        } else if (y >= maxY) {
            x += velX;
            inPosition = true;
        }

        shoot = r.nextInt(100);
        cooldown++;

        // cap number
        cooldown = Game.clamp(cooldown, 0, endCooldown);

        // movement when in position
        if (x <= startX - 90 || x >= Game.WIDTH - 470 + startX - (numberEnemy * 25)) {
            velX *= -1;
        }

        // check hp
        if (HP <= 0) {
            playSE(7);
            handler.removeObject(this);
            HUD.score += 20;
            Wave.setIdEnemy(Wave.getIdEnemy() - 1);
        }

        // shoot
        if (cooldown == endCooldown && shoot <= 5 && inPosition) {
            handler.addObject(new EnemyBullet(x + 12, y + 32, ID.EnemyBullet, handler, 0, 5, 2, bulletImg));

            playSE(3);

            cooldown = 0;
        }

        // check collision
        collision();
    }

    private synchronized void collision() {
        // loop to all object in game
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            // collision player
            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HP--;
                }
            }
            // collision player bullet
            if (tempObject.getId() == ID.PlayerBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HP -= ((PlayerBullet) tempObject).getBulletDamage();
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void playSE(int i) {
        // play sound effect
        playSound.setFile(i);
        playSound.play();
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }
}
