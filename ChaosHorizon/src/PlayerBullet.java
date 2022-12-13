import java.awt.*;

public class PlayerBullet extends GameObject {
    private Handler handler;

    public PlayerBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velY = -10;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 16);
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, 8, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) {
            handler.removeObject(this);
        }
    }
}
