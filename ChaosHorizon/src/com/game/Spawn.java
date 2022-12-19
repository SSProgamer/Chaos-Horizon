package com.game;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int delay;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        delay = 1000;
    }

    public void tick() {
        // cap number
        delay = Game.clamp(delay, 0, 500);

        // new wave when all enemy dead and delay check
        if (Wave.getIdEnemy() == 0) {
            if (delay >= 500 || hud.getWave() >= 5) {
                new Wave(handler, hud, hud.getWave());
                delay = 0;
            } else {
                delay++;
            }
        }
    }
}
