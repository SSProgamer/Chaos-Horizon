import java.awt.*;

public class Player extends GameObject {
    public static int HEALTH = 100;
    Handler handler;

    private boolean shoot;
    private int cooldown;
    private int endCooldown;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        shoot = false;
        endCooldown = 10;
        cooldown = endCooldown;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);

        x += velX;
        y += velY;
        cooldown++;

        x = Game.clamp(x, 0, Game.WIDTH - 280);
        y = Game.clamp(y, 0, Game.HEIGHT - 72);
        cooldown = Game.clamp(cooldown, 0, endCooldown);

        if (shoot && (cooldown == endCooldown)) {
            handler.addObject(new PlayerBullet(x + 28, y - 14, ID.PlayerBullet, handler));
            cooldown = 0;
        }

        collision();
    }

    private synchronized void collision() {
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HEALTH--;
                }
            }
        }
    }

    public boolean isShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 64, 64);
    }
}
