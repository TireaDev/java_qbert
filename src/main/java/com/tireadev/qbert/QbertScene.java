package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;

public class QbertScene extends Scene {

    ShadowEngine se;

    final int qbtstartX = 7 * 32 + 16; // 3
    final int qbtstartY = 3 * 32 - 24; // 0

    int qbtX = qbtstartX;
    int qbtY = qbtstartY;
    int xDirection;
    byte[][] qbrt = new byte[4][];

    static final byte[] tilemap = new byte[] {
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,1,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
    };

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

        // změnit číslo na pozici XY na 0

        // x: 0 → w
        // y: 0 ↓ h

        if (instance.keyPressed('W')){
//            System.out.println("W was pressed");

            // pokud lichý řádek, posuň se nahoru a doleva
            // pokud sudý řádek, pokuň se nahoru
        }
        else if (instance.keyPressed('S')){
//            System.out.println("S was pressed");

            // pokud lichý řádek, posuň se nahoru
            // pokud sudý řádek, posuň se nahoru a doprava
        }
        else if (instance.keyPressed('A')){
//            System.out.println("A was pressed");

            // pokud lichý řádek, posuň se dolů a doleva
            // pokud sudý řádek, posuň se dolů
        }
        else if (instance.keyPressed('D')){
//            System.out.println("D was pressed");

            // pokud lichý řádek, posuň se dolů
            // pokud sudý řádek, posuň se dolů a doprava
        }

        // tilemap[y * 7 + x];
        // změnit číslo na pozici XY na 1
    }
}