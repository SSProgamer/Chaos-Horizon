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
            for (int i = 0; i < 5; i++) {
                handler.addObject(new BasicEnemy(spawnX + 40 * i, spawnY, ID.BasicEnemy, handler));
            }
            hud.setWave(hud.getWave() + 1);
        }
    }
}
