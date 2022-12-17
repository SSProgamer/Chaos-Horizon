package com.game;

import java.awt.*;

public class EnegyBall extends GameObject {
    private HUD hud;
    private Handler handler;
    private PlaySound playSound;

    private int HP;

    public EnegyBall(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        playSound = new PlaySound();

        HP = 20;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect(x, y, 64, 64);
    }

    public void tick() {
        y += velY;

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 10);
        }

        if (y >= 300) {
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 0, 7, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 0, -7, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, 0, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, 0, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, 7, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, 7, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, -7, -7, 1));
            handler.addObject(new EnemyBullet(x + 28, y + 28, ID.EnemyBullet, handler, 7, -7, 1));

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
                    HP -= ((PlayerBullet) tempObject).getDamage();
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