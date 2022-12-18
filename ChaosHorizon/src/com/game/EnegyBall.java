package com.game;

import java.awt.*;

public class EnegyBall extends GameObject {
    private Handler handler;
    private PlaySound playSound;

    private int HP;
    private Image bulletImg;

    public EnegyBall(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        playSound = new PlaySound();
        bulletImg = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/small_ship/SmallBullet.gif");

        HP = 20;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

    public void render(Graphics g) {
        // g.setColor(Color.magenta);
        // g.fillRect(x, y, 64, 64);

        Image img = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/enemy/boss/boss_energy_ball.gif");
        g.drawImage(img, x, y, null);
    }

    public void tick() {
        y += velY;

        if (HP <= 0) {
            handler.removeObject(this);
            HUD.score += 10;
        }

        if (y >= 300) {
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 0, 7, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 0, -7, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, 0, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, 0, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, 7, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, 7, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, -7, 1, bulletImg));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, -7, 1, bulletImg));

            playSE(3);

            handler.removeObject(this);
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
                    ;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void playSE(int i) {
        playSound.setFile(i);
        playSound.play();
    }
}
