import java.awt.*;

public class Wave {
    private Handler handler;
    private HUD hud;
    public Wave(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        for (int i = 0; i < 5; i++) {
            handler.addObject(new BasicEnemy(20 + 40 * i, 80, ID.BasicEnemy, handler, hud));
        }
        hud.setWave(hud.getWave() + 1);
    }
}
