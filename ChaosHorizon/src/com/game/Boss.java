package com.game;

import java.awt.*;

public class Boss extends GameObject {
    private HUD hud;
    private Handler handler;
    private PlaySound playSound;

    private int HP;
    private int cooldown1;
    private int endCooldown1;
    private int cooldown2;
    private int endCooldown2;
    private int cooldown3;
    private int endCooldown3;

    private boolean inPosition;

    public Boss(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        playSound = new PlaySound();

        HP = 1000;
        inPosition = false;
        velX = 1;
        velY = 1;

        endCooldown1 = 150;
        cooldown1 = 0;

        endCooldown2 = 200;
        cooldown2 = 0;

        endCooldown3 = 300;
        cooldown3 = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 400, 64);
    }

    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 400, 64);
    }

    public void tick() {
        cooldown1++;
        cooldown2++;
        cooldown3++;

        cooldown1 = Game.clamp(cooldown1, 0, endCooldown1);
        cooldown2 = Game.clamp(cooldown2, 0, endCooldown2);
        cooldown3 = Game.clamp(cooldown3, 0, endCooldown3);

        if (y < 0) {
            y += velY;
        } else {
            inPosition = true;
        }

        if (inPosition) {
            x += velX;
        }

        if (x >= 150 || x <= 0) {
            velX *= -1;
        }

        if (HP <= 0) {
            playSE(7);
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 1000);
        }

        if (cooldown1 == endCooldown1 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 11, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);

            cooldown1 = 0;
        } else if (cooldown1 == 100 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 5, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 6, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);
        } else if (cooldown1 == 110 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 4, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 7, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);
        } else if (cooldown1 == 120 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 3, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 8, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);
        } else if (cooldown1 == 130 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 2, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 9, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);
        } else if (cooldown1 == 140 && inPosition) {
            handler.addObject(new EnemyBullet(x + 20 + 32 * 1, y + 64, ID.EnemyBullet, handler, 0, 5, 2));
            handler.addObject(new EnemyBullet(x + 20 + 32 * 10, y + 64, ID.EnemyBullet, handler, 0, 5, 2));

            playSE(3);
        }

        if (cooldown2 == endCooldown2 && inPosition) {
            handler.addObject(new EnemyBullet(x + 88, y + 64, ID.EnemyBullet, handler, 0, 3, 5));
            handler.addObject(new EnemyBullet(x + 112, y + 64, ID.EnemyBullet, handler, 0, 3, 5));
            handler.addObject(new EnemyBullet(x + 64, y + 64, ID.EnemyBullet, handler, -1, 3, 5));
            handler.addObject(new EnemyBullet(x + 136, y + 64, ID.EnemyBullet, handler, 1, 3, 5));

            handler.addObject(new EnemyBullet(x + 288, y + 64, ID.EnemyBullet, handler, 0, 3, 5));
            handler.addObject(new EnemyBullet(x + 312, y + 64, ID.EnemyBullet, handler, 0, 3, 5));
            handler.addObject(new EnemyBullet(x + 264, y + 64, ID.EnemyBullet, handler, -1, 3, 5));
            handler.addObject(new EnemyBullet(x + 336, y + 64, ID.EnemyBullet, handler, 1, 3, 5));

            playSE(3);

            cooldown2 = 0;
        }
        if (cooldown3 == endCooldown3 && inPosition) {
            handler.addObject(new EnegyBall(x + 173, y, ID.EnegyBall, handler, hud));

            cooldown3 = 0;

            playSE(3);
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
                    HP -= PlayerBullet.damage;
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
