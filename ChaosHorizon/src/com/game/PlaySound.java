package com.game;

import javax.sound.sampled.*;
import java.io.File;

public class PlaySound {
    Clip clip;
    File soundFile[] = new File[30];

    public PlaySound() {
        soundFile[0] = new File("ChaosHorizon/res/sound/Music1.wav");
        soundFile[1] = new File("ChaosHorizon/res/sound/Music2.wav");
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
