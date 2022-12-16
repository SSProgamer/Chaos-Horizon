package com.game;

import java.awt.*;
import java.awt.image.*;

public class Game extends Canvas implements Runnable {
    // Set screen size
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private PlaySound playSound = new PlaySound();
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private MainMenu menu;
    private EndGame end;
    private BackgroundInGame backgroundInGame;

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
        handler = new Handler();
        menu = new MainMenu(this, handler);
        end = new EndGame(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(end);
        new Window(WIDTH, HEIGHT, "Chaos Horizon", this);

        backgroundInGame = new BackgroundInGame(0, -635, ID.BackgroundInGame);
        hud = new HUD();
        spawner = new Spawn(handler, hud);

        playMusic(0);

        // Check State
        if (gameState == STATE.Game) {
            // handler create play
            handler.addObject(new Player(600 / 2 - 64, HEIGHT - 128, ID.Player, handler));
        }

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
        handler.tick();

        // check State
        if (gameState == STATE.Game) {
            hud.tick();
            backgroundInGame.tick();
            spawner.tick();
        } else if (gameState == STATE.Menu) {
            menu.tick();
        } else if (gameState == STATE.Win || gameState == STATE.Lose) {
            end.tick();
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
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public void playMusic(int i) {
        playSound.setFile(i);
        playSound.play();
        playSound.loop();
    }

    public void stopMusic() {
        playSound.stop();
    }

    public static void main(String[] args) {
        new Game();
    }
}
