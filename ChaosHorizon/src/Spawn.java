import java.net.SocketTimeoutException;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int spawnX = 20;
    private int spawnY = 20;
    private BasicEnemy basicEnemy;
    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        
    }

    public void tick() {
        if (basicEnemy.getEnemyaLive() == 0) {
            new Wave(handler, hud, hud.getWave());
            /*for (int i = 0; i < 5; i++) {
                handler.addObject(new BasicEnemy(spawnX + 40 * i, spawnY, ID.BasicEnemy, handler, hud));
            }
            hud.setWave(hud.getWave() + 1);*/
        }
        /*else if(hud.getWave() == 1 && basicEnemy.getEnemyaLive() == 0){
            new Wave(handler, hud, 2);
        }
        else if(hud.getWave() == 2 && basicEnemy.getEnemyaLive() == 0){
            new Wave(handler, hud, 3);
        }
        else if(hud.getWave() == 3 && basicEnemy.getEnemyaLive() == 0){
            new Wave(handler, hud, 4);
        }
        else if(hud.getWave() == 4 && basicEnemy.getEnemyaLive() == 0){
            new Wave(handler, hud, 5);
        }*/
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

}
