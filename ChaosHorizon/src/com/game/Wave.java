package com.game;

import java.awt.*;

public class Wave {
    private Handler handler;
    private HUD hud;
    // private int num = 20;
    private static int idEnemy = 0;
    private static int EnemyaLive = 0;
    private BasicEnemy basicEnemy;
    private HeavyEnemy heavyEnemy;
    private FastEnemy fastEnemy;

    public Wave(Handler handler, HUD hud, int wave) {
        idEnemy = 0;
        this.handler = handler;
        this.hud = hud;
        // เริ่มนับ Enemy ใหม่
        basicEnemy.setNumberEnemy(0);
        // กำหนด wave
        if (wave == 0) {
            Wave1();
        } else if (wave == 1) {
            Wave2();
        } else if (wave == 2) {
            Wave3();
        } else if (wave == 3) {
            Wave4();
        } else if (wave == 4) {
            Wave2();
        }
        hud.setWave(hud.getWave() + 1);
    }

    public void Wave1() {
        for (int i = 0; i < 8; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }

    public void Wave2() {
        for (int i = 0; i < 10; i++) {
            fastEnemy = new FastEnemy(-50 * i, 300, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(fastEnemy);
            idEnemy++;
            fastEnemy.setEnemyPosition(fastEnemy.getidEnemy());
        }
        for (int i = 0; i < 8; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }

    public void Wave3() {
        for (int i = 0; i < 4; i++) {
            heavyEnemy = new HeavyEnemy(50 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(heavyEnemy);
            idEnemy++;
            heavyEnemy.setEnemyPosition(heavyEnemy.getidEnemy());
        }
        for (int i = 0; i < 8; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }
    public void Wave4() {
        for (int i = 0; i < 8; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
        for (int i = 0; i < 10; i++) {
            fastEnemy = new FastEnemy(-50 * i, 300, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(fastEnemy);
            idEnemy++;
            fastEnemy.setEnemyPosition(fastEnemy.getidEnemy());
        }
        for (int i = 0; i < 4; i++) {
            heavyEnemy = new HeavyEnemy(50 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(heavyEnemy);
            idEnemy++;
            heavyEnemy.setEnemyPosition(heavyEnemy.getidEnemy());
        }
    }

    public static int getIdEnemy() {
        return idEnemy;
    }

    public static void setIdEnemy() {
        Wave.idEnemy--;
    }

}
