package com.game;

import java.awt.event.*;
import java.io.*;

public class WindowEventHandler extends WindowAdapter {
    private String saveString;

    public WindowEventHandler() {
        saveString = "";
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try (FileOutputStream fout = new FileOutputStream("Data.dat");) {
            for (int i = 0; i < String.valueOf(Game.highestScore).length(); i++)
                fout.write(String.valueOf(Game.highestScore).charAt(i));
        } catch (IOException ei) {
            System.out.println(ei);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        try (FileInputStream fin = new FileInputStream("Data.dat");) {
            int i = fin.read();
            while (i != -1) {
                saveString = saveString + (char) i;
                i = fin.read();
            }
        } catch (IOException ei) {
            System.out.println(ei);
        }
        try {
            Game.highestScore = Integer.parseInt(saveString);
        } catch (NumberFormatException en) {
            en.printStackTrace();
        }
    }
}
