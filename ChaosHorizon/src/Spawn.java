public class Spawn {
    private Handler handler;
    private HUD hud;
    private int spawnX = 20;
    private int spawnY = 20;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
        if (hud.getWave() == 0) {
            new Wave(handler, hud);
            for (int i = 0; i < 5; i++) {
                handler.addObject(new BasicEnemy(spawnX + 40 * i, spawnY, ID.BasicEnemy, handler, hud));
            }
            hud.setWave(hud.getWave() + 1);
        }
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
