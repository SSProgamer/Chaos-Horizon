package com.game;

import java.util.*;
import java.awt.*;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        // loop to all game object tick
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        // loop to all game object render
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        // add game object
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        // remove game object
        this.object.remove(object);
    }
}
