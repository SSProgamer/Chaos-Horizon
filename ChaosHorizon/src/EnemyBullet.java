import java.awt.*;

public class EnemyBullet extends GameObject {
    private Handler handler;

    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 8, 8);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x >= Game.HEIGHT) {
            handler.removeObject(this);
        }
    }

}
