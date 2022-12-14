package com.game;

import java.awt.*;
import java.awt.image.*;

public class Game extends Canvas implements Runnable {
    // Set screen size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private PlaySound playSound;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private MainMenu menu;
    private EndGame end;
    private BackgroundInGame backgroundInGame;
    private MouseEventHandler mouseHandler;

    public static int highestScore;

    // Make state variable
    public enum STATE {
        Menu,
        Game,
        Help,
        Win,
        Lose
    };

    // set State
    public STATE gameState = STATE.Menu;

    public Game() {
        playSound = new PlaySound();
        handler = new Handler();
        menu = new MainMenu(this);
        end = new EndGame(this);
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        mouseHandler = new MouseEventHandler(this);
        backgroundInGame = new BackgroundInGame(0, -635, ID.BackgroundInGame);

        // add listener
        this.addMouseListener(mouseHandler);
        this.addKeyListener(new KeyInput(handler, this));

        // make window
        new Window(WIDTH, HEIGHT, "Chaos Horizon", this);

        // music start
        playMusic(1);

        // handler create play
        handler.addObject(new Player(600 / 2 - 64, HEIGHT - 128, ID.Player, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        // too hard to explain
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        // order the work of all objects in the game
        handler.tick();
        // win
        if (hud.getWave() >= 6) {
            // build all object in new game
            handler = new Handler();
            hud = new HUD();
            spawner = new Spawn(handler, hud);

            this.addKeyListener(new KeyInput(handler, this));

            end.setEndScore(HUD.score);

            if (HUD.score > highestScore) {
                highestScore = HUD.score;
            }

            HUD.score = 0;
            Player.HEALTH = 100;
            handler.addObject(new Player(600 / 2 - 64, HEIGHT - 128, ID.Player, handler));
            Wave.setIdEnemy(0);
            gameState = STATE.Win;
        }
        // lose
        if (Player.HEALTH <= 0) {
            playSE(7);
            // build all object in new game
            handler = new Handler();
            hud = new HUD();
            spawner = new Spawn(handler, hud);

            this.addKeyListener(new KeyInput(handler, this));

            end.setEndScore(HUD.score);

            if (HUD.score > highestScore) {
                highestScore = HUD.score;
            }

            HUD.score = 0;
            Player.HEALTH = 100;
            handler.addObject(new Player(600 / 2 - 64, HEIGHT - 128, ID.Player, handler));
            Wave.setIdEnemy(0);
            gameState = STATE.Lose;
        }
        // check State
        if (gameState == STATE.Game) {
            // object work in every tick
            hud.tick();
            backgroundInGame.tick();
            spawner.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // check State
        if (gameState == STATE.Game) {
            backgroundInGame.render(g);
            handler.render(g);
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help) {
            menu.render(g);
        } else if (gameState == STATE.Win) {
            end.render(g);
        } else if (gameState == STATE.Lose) {
            end.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        // cap number
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public void playMusic(int i) {
        // play music
        playSound.setFile(i);
        playSound.play();
        playSound.loop();
    }

    public void stopMusic() {
        // stop music
        playSound.stop();
    }

    public void playSE(int i) {
        // play sound effect
        playSound.setFile(i);
        playSound.play();
    }

    public static void main(String[] args) {
        // start game
        new Game();
    }
}
