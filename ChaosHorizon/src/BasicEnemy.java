import java.awt.*;

public class BasicEnemy extends GameObject {
    private int HP = 20;
    private int startX;
    private int startY;
    private HUD hud;
    Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;

        startX = x;
        startY = y;

        velX = 3;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }

    public void tick() {
        x += velX;

        if (x <= startX || x >= Game.WIDTH - 470 + startX) {
            velX *= -1;
        }

        if (HP <= 0) {
            handler.removeObject(this);
            hud.setScore(hud.getScore() + 20);
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
