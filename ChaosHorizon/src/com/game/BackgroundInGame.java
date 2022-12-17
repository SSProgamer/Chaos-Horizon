package com.game;

import java.awt.*;

public class BackgroundInGame extends GameObject {
    public BackgroundInGame(int x, int y, ID id) {
        super(x, y, id);
        this.id = id;
        velX = 3;
        velY = 1;
    }

    public void render(Graphics g) {
        Image img1 = Toolkit.getDefaultToolkit().getImage("ChaosHorizon/res/background-ingame.png");
        g.drawImage(img1, x, y, null);
    }

    public void tick() {
        y += velY;
        if (y == -35) {
            y = -635;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 0, 0);
    }
}