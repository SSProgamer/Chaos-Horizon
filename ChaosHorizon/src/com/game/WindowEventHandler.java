package com.game;

import java.awt.event.*;

public class WindowEventHandler extends WindowAdapter {
    @Override
    public void windowOpened(WindowEvent e) {
        System.out.println("yes");
    }
}
