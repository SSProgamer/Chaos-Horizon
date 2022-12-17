package com.game;

public class Wave {
    private Handler handler;
    private HUD hud;
    // private int num = 20;
    private static int idEnemy = 0;
    private static int EnemyaLive = 0;
    private BasicEnemy basicEnemy;
    private HeavyEnemy heavyEnemy;
    private FastEnemy fastEnemy;
    private Boss boss;

    public Wave(Handler handler, HUD hud, int wave) {
        idEnemy = 0;
        this.handler = handler;
        this.hud = hud;
        // เริ่มนับ Enemy ใหม่
        BasicEnemy.setNumberEnemy(0);
        // กำหนด wave
        if (wave == 0) {
            wave1();
        } else if (wave == 1) {
            wave2();
        } else if (wave == 2) {
            wave3();
        } else if (wave == 3) {
            wave4();
        } else if (wave == 4) {
            wave5();
        }
        hud.setWave(hud.getWave() + 1);
    }

    public void wave1() {
        for (int i = 0; i < 5; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(100 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }

    public void wave2() {
        for (int i = 0; i < 10; i++) {
            fastEnemy = new FastEnemy(-50 * i, 300, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(fastEnemy);
            idEnemy++;
            fastEnemy.setEnemyPosition(fastEnemy.getidEnemy());
        }
        for (int i = 0; i < 5; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(100 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }

    public void wave3() {
        for (int i = 0; i < 4; i++) {
            heavyEnemy = new HeavyEnemy(165 + 40 * i, -120, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(heavyEnemy);
            idEnemy++;
            heavyEnemy.setEnemyPosition(heavyEnemy.getidEnemy());
        }
        for (int i = 0; i < 7; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(100 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }

    public void wave4() {
        for (int i = 0; i < 4; i++) {
            heavyEnemy = new HeavyEnemy(165 + 40 * i, -120, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(heavyEnemy);
            idEnemy++;
            heavyEnemy.setEnemyPosition(heavyEnemy.getidEnemy());
        }
        for (int i = 0; i < 9; i++) {
            // สร้าง enemy
            EnemyaLive++;
            basicEnemy = new BasicEnemy(100 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
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
    }

    public void wave5() {
        boss = new Boss(75, -96, ID.Boss, handler, hud);
        handler.addObject(boss);
        idEnemy++;
    }

    public static int getIdEnemy() {
        return idEnemy;
    }

    public static void setIdEnemy() {
        Wave.idEnemy--;
    }

}
