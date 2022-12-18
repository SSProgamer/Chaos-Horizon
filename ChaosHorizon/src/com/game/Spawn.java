package com.game;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int spawnX;
    private int spawnY;
    private int delay;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        spawnX = 20;
        spawnY = 20;
        delay = 1000;
    }

    public void tick() {
        delay = Game.clamp(delay, 0, 500);

        if (Wave.getIdEnemy() == 0) {
            if (delay >= 500) {
                new Wave(handler, hud, hud.getWave());
                delay = 0;
            } else {
                delay++;
            }
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
