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

    public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;

        HP = 20;
        r = new Random();

        startX = x;
        startY = y;

        velX = 3;
        velY = 3;

        endCooldown = 100;
        cooldown = endCooldown;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        shoot = r.nextInt(100);
        cooldown++;

        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (x <= startX || x >= Game.WIDTH - 470 + startX) {
            velX *= -1;
        }

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 20);
        }

        if (cooldown == endCooldown && shoot <= 5) {
            handler.addObject(new EnemyBullet(x, y, ID.EnemyBullet, handler));
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
                    HP--;
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
