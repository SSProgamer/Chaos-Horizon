package com.game;

import java.awt.*;

public class Player extends GameObject {
    // Player health
    public static int HEALTH = 100;
    private Handler handler;
    private PlaySound playSound;

    private boolean shoot;
    private int endCooldown;
    private int cooldown;
    private int d;

    // Damage of out bullet | 1 -> 2 -> 3
    public static int damage;
    // Rate of fire | 1 -> 2 -> 3
    public static int raf;
    // How many bullet we shoot | 1 -> 2 -> 3
    public static int ammo;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playSound = new PlaySound();

        shoot = false;
        endCooldown = 35;
        cooldown = endCooldown;

        damage = 1;
        raf = 1;
        ammo = 1;
    }

    public void playerPost(int d) {
        this.d = d;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 8, y + 8, 48, 48);
    }

    public void tick() {
        // cap number
        HEALTH = Game.clamp(HEALTH, 0, 100);

        endCooldown = 35 - (raf * 5);
        cooldown++;

        // movement
        x += velX;
        y += velY;

        // cap number
        x = Game.clamp(x, 0, Game.WIDTH - 312);
        y = Game.clamp(y, 0, Game.HEIGHT - 104);
        cooldown = Game.clamp(cooldown, 0, endCooldown);

        // shoot
        if (shoot && (cooldown == endCooldown)) {
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 0, -10, damage));
            if (ammo >= 2) {
                handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 5, -10, damage));
                handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, -5, -10, damage));
            }
            if (ammo >= 3) {
                handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 10, -10, damage));
                handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, -10, -10, damage));
            }

            playSE(2);

            cooldown = 0;
        }

        // check collision
        collision();
    }

    public void render(Graphics g) {
        if (d == 1) {
            // go left
            Image left = Toolkit.getDefaultToolkit()
                    .getImage("ChaosHorizon/res/player/ship/player_turn_left_hold64.gif");
            g.drawImage(left, x, y, null);
        } else if (d == 2) {
            // go right
            Image right = Toolkit.getDefaultToolkit()
                    .getImage("ChaosHorizon/res/player/ship/player_turn_right_hold64.gif");
            g.drawImage(right, x, y, null);
        } else {
            // normal
            Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/player/ship/player_idle64.gif");
            g.drawImage(img, x, y, null);
        }
    }

    private synchronized void collision() {
        // loop to all object in game
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.HeavyEnemy || tempObject.getId() == ID.Boss) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // Collision with Enemy
                    HEALTH -= 5;
                }
            }
            if (tempObject.getId() == ID.EnegyBall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // Collision with EnegyBall
                    HEALTH -= 3;
                }
            }
            if (tempObject.getId() == ID.EnemyBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // Collision with EnemyBullet
                    HEALTH -= ((EnemyBullet) tempObject).getDamage();
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

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
}
