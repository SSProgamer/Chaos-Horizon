package com.game;

import java.awt.*;

public class Player extends GameObject {
    public static int HEALTH = 100;
    private Handler handler;
    private PlaySound playSound;

    private boolean shoot;
    private int cooldown;
    private int endCooldown;
    private int d;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playSound = new PlaySound();

        shoot = false;
        endCooldown = 20;
        cooldown = endCooldown;
    }

    public void playerPost(int d) {
        this.d = d;
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
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 0, -10));
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 5, -10));
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, -5, -10));
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, 10, -10));
            handler.addObject(new PlayerBullet(x + 26, y - 20, ID.PlayerBullet, handler, -10, -10));

            playSE(2);

            cooldown = 0;
        }

        collision();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (d == 1) {
            Image left = Toolkit.getDefaultToolkit()
                    .getImage("ChaosHorizon/res/player/ship/player_turn_left_hold64.gif");
            g.drawImage(left, x, y, null);
        } else if (d == 2) {
            Image right = Toolkit.getDefaultToolkit()
                    .getImage("ChaosHorizon/res/player/ship/player_turn_right_hold64.gif");
            g.drawImage(right, x, y, null);
        } else {
            Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/player/ship/player_idle64.gif");
            g.drawImage(img1, x, y, null);
        }

        // g.setColor(Color.white);
        // g.fillRect(x, y, 64, 64);

        g.setColor(Color.green);
        g2d.draw(getBounds());

    }

    private synchronized void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy
                    || tempObject.getId() == ID.HeavyEnemy || tempObject.getId() == ID.Boss
                    || tempObject.getId() == ID.EnegyBall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // ลองหักเลือดเยอะขึ้นเมื่อชน
                    HEALTH -= 5;
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

    public void playSE(int i) {
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
