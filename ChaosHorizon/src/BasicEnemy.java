import java.awt.*;
import java.util.*;

public class BasicEnemy extends GameObject {
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
    private ID id;
    private int maxY;
    private int maxedY = 0;
    private static int numberEnemy = 0;

    public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud, int idEnemy) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.id = id;
        this.idEnemy = idEnemy;
        numberEnemy++;
        HP = 5;
        r = new Random();
        startX = x;
        startY = y;

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
            maxY = 80;
        } else {
            maxY = 150;
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }

    public void tick() {
        if (y <= maxY) {
            maxedY += velY;
            y += velY;
        } else if (maxedY <= 210) {
            maxedY += velY;
        } else if (y >= maxY) {
            x += velX;
        }

        shoot = r.nextInt(100);
        cooldown++;

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (x <= startX - 10 || x >= Game.WIDTH - 470 + startX - (numberEnemy * 20)) {
            velX *= -1;
        }

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 20);
        }

        if (cooldown == endCooldown && shoot <= 5) {
            handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, handler, 0, 5));
            handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, handler, 5, 5));
            handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, handler, -5, 5));
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
