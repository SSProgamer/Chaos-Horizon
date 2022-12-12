import java.awt.*;

public class HUD {

    public void tick() {
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(550, 0, 250, 600);
        g.setColor(Color.green);
        g.fillRect(570, 25, Player.HEALTH * 2, 30);
        g.setColor(Color.white);
        g.drawRect(570, 25, 200, 30);
    }
}
