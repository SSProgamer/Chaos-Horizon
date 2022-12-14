import java.awt.*;

public class Wave {
    private Handler handler;
    private HUD hud;
    //private int num = 20;
    private int idEnemy = 1;
    private BasicEnemy basicEnemy;

    public Wave(Handler handler, HUD hud, int wave) {
        idEnemy = 1;
        this.handler = handler;
        this.hud = hud;
        //เริ่มนับ Enemy ใหม่
        basicEnemy.setNumberEnemy(0);
        //กำหนด wave
        if (wave == 0){
            Wave1();
        }
        else if(wave == 1){
            Wave2();
        }
        else if(wave == 2){
            Wave2();
        }
        else if(wave == 3){
            Wave2();
        }
        else if(wave == 4){
            Wave2();
        }
        hud.setWave(hud.getWave() + 1);
    }
    public void Wave1(){
        for (int i = 0; i < 8; i++) {
            //สร้าง enemy
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }
    public void Wave2(){
        for (int i = 0; i < 4; i++) {
            basicEnemy = new BasicEnemy(20 + 40 * i, -60, ID.BasicEnemy, handler, hud, idEnemy);
            handler.addObject(basicEnemy);
            idEnemy++;
            basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
        for (int i = 0; i < 8; i++) {
            //new Enemy
            idEnemy++;
            //basicEnemy.setEnemyPosition(basicEnemy.getidEnemy());
        }
    }
}
