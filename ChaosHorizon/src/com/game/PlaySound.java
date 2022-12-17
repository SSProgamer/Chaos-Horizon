package com.game;

import javax.sound.sampled.*;
import java.io.File;

public class PlaySound {
    Clip clip;
    File soundFile[] = new File[30];

    public PlaySound() {
        soundFile[0] = new File("ChaosHorizon/res/sound/Music1.wav");
        soundFile[1] = new File("ChaosHorizon/res/sound/Music2.wav");
        soundFile[2] = new File("ChaosHorizon/res/sound/Gun1.wav");
        soundFile[3] = new File("ChaosHorizon/res/sound/Gun2.wav");
        soundFile[4] = new File("ChaosHorizon/res/sound/Bomb1.wav");
        soundFile[5] = new File("ChaosHorizon/res/sound/Bomb2.wav");
        soundFile[6] = new File("ChaosHorizon/res/sound/Button1.wav");
        soundFile[7] = new File("ChaosHorizon/res/sound/Dead.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
