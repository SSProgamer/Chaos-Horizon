package com.game;

import java.awt.*;

public class Boss extends GameObject {
    private Handler handler;
    private PlaySound playSound;

    private int HP;
    private int cooldown1;
    private int endCooldown1;
    private int cooldown2;
    private int endCooldown2;
    private int cooldown3;
    private int endCooldown3;
    private Image bulletImg1;
    private Image bulletImg2;

    private boolean inPosition;

    public Boss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playSound = new PlaySound();
        bulletImg1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/medium_ship/MediumBullet.gif");
        bulletImg2 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/big_ship/BigBullet.gif");

        HP = 1000;
        inPosition = false;
        velX = 1;
        velY = 1;

        endCooldown1 = 100;
        cooldown1 = 0;

        endCooldown2 = 150;
        cooldown2 = 0;

        endCooldown3 = 275;
        cooldown3 = 0;
    }

    public Rectangle getBounds() {
        // set hit-box
        return new Rectangle(x + 72, y, 254, 64);
    }

    public void render(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/boss/boss.gif");
        g.drawImage(img, x, y, null);
    }

    public void tick() {
        if (inPosition) {
            cooldown1++;
            cooldown2++;
            cooldown3++;
        }

        // cap number
        cooldown1 = Game.clamp(cooldown1, 0, endCooldown1);
        cooldown2 = Game.clamp(cooldown2, 0, endCooldown2);
        cooldown3 = Game.clamp(cooldown3, 0, endCooldown3);

        // go to position
        if (y < 0) {
            y += velY;
        } else {
            inPosition = true;
        }

        // movement when in position
        if (inPosition) {
            x += velX;
        }
        if (x >= 222 || x <= -72) {
            velX *= -1;
        }

        // check hp
        if (HP <= 0) {
            playSE(7);
            handler.removeObject(this);
            HUD.score += 1000;
            Wave.setIdEnemy(Wave.getIdEnemy() - 1);
        }

        // shoot
        if (cooldown1 == endCooldown1 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 2, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 9, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));

            playSE(3);

            cooldown1 = 0;
        } else if (cooldown1 == endCooldown1 - 30 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 5, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 6, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));

            playSE(3);
        } else if (cooldown1 == endCooldown1 - 20 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 4, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 7, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));

            playSE(3);
        } else if (cooldown1 == endCooldown1 - 10 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 3, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 8, y + 64, ID.EnemyBullet, handler, 0, 5, 2, bulletImg1));

            playSE(3);
        }

        if (cooldown2 == endCooldown2 && inPosition) {
            handler.addObject(new EnemyBullet(x + 88, y + 64, ID.EnemyBullet, handler, 0,
                    3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 112, y + 64, ID.EnemyBullet, handler,
                    0, 3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 64, y + 64, ID.EnemyBullet, handler,
                    -1, 3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 136, y + 64, ID.EnemyBullet, handler,
                    1, 3, 5, bulletImg2));

            handler.addObject(new EnemyBullet(x + 288, y + 64, ID.EnemyBullet, handler,
                    0, 3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 312, y + 64, ID.EnemyBullet, handler,
                    0, 3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 264, y + 64, ID.EnemyBullet, handler,
                    -1, 3, 5, bulletImg2));
            handler.addObject(new EnemyBullet(x + 336, y + 64, ID.EnemyBullet, handler,
                    1, 3, 5, bulletImg2));

            playSE(3);

            cooldown2 = 0;
        }

        if (cooldown3 == endCooldown3 && inPosition) {
            handler.addObject(new EnegyBall(x + 168, y, ID.EnegyBall, handler));

            cooldown3 = 0;

            playSE(3);
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
