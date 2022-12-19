package com.game;

import java.awt.*;
import java.util.*;

public class FastEnemy extends GameObject {
    private Handler handler;
    private PlaySound playSound;

    private int HP;
    private int cooldown;
    private int endCooldown;
    private int shoot;
    private Random r;
    private int idEnemy;
    private boolean inPosition;
    private static int numberEnemy = 0;
    private Image bulletImg;

    public FastEnemy(int x, int y, ID id, Handler handler, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.idEnemy = idEnemy;
        numberEnemy++;
        HP = 5;
        r = new Random();
        inPosition = true;
        velX = 5;
        velY = 5;
        playSound = new PlaySound();
        bulletImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/small_ship/SmallBullet.gif");

        endCooldown = 75;
        cooldown = endCooldown;
    }

    public int getidEnemy() {
        return idEnemy;
    }

    public static void setNumberEnemy(int numberEnemy) {
        FastEnemy.numberEnemy = numberEnemy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void render(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/small_ship/small_enemy_16px.gif");
        g.drawImage(img, x, y, null);
    }

    public void tick() {
        // movement
        if (x <= 0) {
            x += velX;
        } else {
            y += velY;
            x += velX;
        }

        shoot = r.nextInt(100);
        cooldown++;

        // cap
        cooldown = Game.clamp(cooldown, 0, endCooldown);

        // movement
        if (x <= numberEnemy * -45 || x >= Game.WIDTH) {
            velX *= -1;
        }
        if (y <= 100 || y >= 300) {
            velY *= -1;
        }

        // check hp
        if (HP <= 0) {
            playSE(7);
            handler.removeObject(this);
            HUD.score += 10;
            Wave.setIdEnemy(Wave.getIdEnemy() - 1);
        }

        // shoot
        if (cooldown == endCooldown && shoot <= 5 && inPosition) {
            handler.addObject(new EnemyBullet(x + 4, y + 16, ID.EnemyBullet, handler, 0, 7, 1, bulletImg));

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
                    ;
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
