import java.awt.*;

public class Player extends GameObject {
    public static int HEALTH = 100;
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);

        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 280);
        y = Game.clamp(y, 0, Game.HEIGHT - 72);

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

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 30, 30);
    }
}
