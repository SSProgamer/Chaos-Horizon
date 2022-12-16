package com.game;

import java.net.URL;
import javax.sound.sampled.*;
import java.io.File;

public class PlaySound {
    Clip clip;
    URL soundURL[] = new URL[30];
    File soundFile[] = new File[30];
    
    
    // String absolutePath = soundFile.getAbsolutePath();

    public PlaySound() {
        soundFile[0] = new File("ChaosHorizon/res/sound/Music1.wav");
        
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile[i]);
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
