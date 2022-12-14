import java.awt.*;

public class EnemyBullet extends GameObject {
    private Handler handler;
    private int damage;

    public EnemyBullet(int x, int y, ID id, Handler handler, int velX, int velY, int damage) {
        super(x, y, id);
        this.handler = handler;
        this.velX = velX;
        this.velY = velY;
        this.damage = damage;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.yellow);
        g.fillRect(x, y, 8, 8);

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

    public int getDamage() {
        return damage;
    }
}
