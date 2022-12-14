import java.awt.*;

public class EnemyBullet extends GameObject {
    private Handler handler;

    public EnemyBullet(int x, int y, ID id, Handler handler, int velX, int velY) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
    }

    public Rectangle getBounds() {
        return new Rectangle(x + 12, y + 32, 8, 8);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.yellow);
        g.fillRect(x + 12, y + 32, 8, 8);

        g.setColor(Color.green);
        g2d.draw(getBounds());
    }

    public void tick() {
        x += velX;
        y += velY;

        if (x >= Game.HEIGHT) {
            handler.removeObject(this);
        }
    }

}
