package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;

public class QbertScene extends Scene {

    ShadowEngine se;

    final int qbtstartX = 7 * 32 + 16;
    final int qbtstartY = 3 * 32 - 24;

    int qbtX = qbtstartX;
    int qbtY = qbtstartY;
    int xDirection;
    byte[][] qbrt = new byte[4][];


    public QbertScene(ShadowEngine instance) {
        super(instance);
        this.se = this.instance;
    }

    public void respawnQbt(){
        this.qbtX = this.qbtstartX;
        this.qbtY = this.qbtstartY;
    }

    @Override
    public void onAwake() {
        qbrt[0] = se.getSubImage(atlas, 5*16, 0, 16, 16);
    }

    @Override
    public void onUpdate(float v) {
        if (instance.keyPressed('W')){
            System.out.println("W was pressed");
        }
        else if (instance.keyPressed('S')){
            System.out.println("S was pressed");
        }
        else if (instance.keyPressed('A')){
            System.out.println("A was pressed");
        }
        else if (instance.keyPressed('D')){
            System.out.println("D was pressed");
        }
    }
}