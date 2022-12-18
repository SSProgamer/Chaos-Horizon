package com.game;

import java.awt.*;
import java.util.*;

public class HeavyEnemy extends GameObject {
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
    private Image bulletImg;

    public HeavyEnemy(int x, int y, ID id, Handler handler, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.idEnemy = idEnemy;
        HP = 30;
        r = new Random();
        startX = x;
        inPosition = false;
        playSound = new PlaySound();
        bulletImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/big_ship/BigBullet.gif");

        velX = 1;
        velY = 1;

        endCooldown = 200;
        cooldown = endCooldown;
    }

    public int getidEnemy() {
        return idEnemy;
    }

    public static void setNumberEnemy(int numberEnemy) {
    }

    public void setEnemyPosition(int idEnemy) {
        if (idEnemy % 2 == 0) {
            maxY = 90;
            x = x + 40;
            y = y + 70;
            maxedY = -150;
            startX = startX + 40;
        } else {
            maxY = 20;
            maxedY = -150;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 48);
    }

    public void render(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/big_ship/big_enemy.gif");
        g.drawImage(img, x, y, null);
        // g.setColor(Color.pink);
        // g.fillRect(x, y, 48, 48);
    }

    public void tick() {
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

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (x <= startX - 200 || x >= Game.WIDTH - 470 + startX - 135) {
            velX *= -1;
        }

        if (HP <= 0) {
            playSE(7);
            handler.removeObject(this);
            HUD.score += 40;
            Wave.setIdEnemy(Wave.getIdEnemy() - 1);
        }

        if (cooldown == endCooldown && shoot <= 5 && inPosition) {
            handler.addObject(new EnemyBullet(x + 4, y + 48, ID.EnemyBullet, handler, 0, 3, 4, bulletImg));
            handler.addObject(new EnemyBullet(x + 36, y + 48, ID.EnemyBullet, handler, 0, 3, 4, bulletImg));

            playSE(3);

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
                    HP -= ((PlayerBullet) tempObject).getBulletDamage();
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void playSE(int i) {
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
