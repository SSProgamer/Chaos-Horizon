package com.game;

import java.net.URL;
import javax.sound.sampled.*;

public class PlaySound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public PlaySound() {
        soundURL[0] = getClass().getResource("Music1.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println("yes");
        } catch (Exception e) {
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
