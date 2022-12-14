import java.awt.*;

public class Wave {
    private Handler handler;
    private HUD hud;
    private int num = 20;
    private int idEnemy = 1;
    private BasicEnemy basicEnemy;

    public Wave(Handler handler, HUD hud, int wave) {
        idEnemy = 1;
        this.handler = handler;
        this.hud = hud;
        for (int i = 0; i < 8; i++) {
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
        hud.setWave(hud.getWave() + 1);
    }
}
