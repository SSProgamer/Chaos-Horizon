import java.awt.*;

public class PlayerBullet extends GameObject {
    private int cooldown;
    private boolean shoot;
    Handler handler;

    public PlayerBullet(int x, int y, ID id) {
        super(x, y, id);

        cooldown = 1000;
        shoot = false;
        velY = -5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 2, 2);
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 2, 2);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) {
            handler.removeObject(this);
        }

        if (shoot) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);

                handler.addObject(new PlayerBullet(tempObject.getX(), tempObject.getY(), ID.PlayerBullet));
            }
        }
    }
}
