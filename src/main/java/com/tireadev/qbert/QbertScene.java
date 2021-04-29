package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.Main.scale;

public class QbertScene extends Scene {

    ShadowEngine se;

    final int qbtstartX = 2;
    final int qbtstartY = 3;

    int x = qbtstartX;
    int y = qbtstartY;
    byte[] qbrt;

    static final byte[] tilemap = new byte[] {
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
    };

    public QbertScene(ShadowEngine instance) {
        super(instance);
    }

    public void respawnQbt(){
        x = this.qbtstartX;
        y = this.qbtstartY;
    }

    @Override
    public void onAwake() {
        se = instance;

        qbrt = se.getSubImage(atlas, 5*16, 0, 16, 16);
    }

    boolean isEven; // sudý

    @Override
    public void onUpdate(float v) {

        // x: 0 → w
        // y: 0 ↓ h

        tilemap[y * 7 + x] = 0; // změnit číslo na pozici XY na 0

        isEven = (y * 7 + 1) % 2 == 0;

        if (instance.keyPressed('W')){
//            System.out.println("W was pressed");
            if (isEven) {
            // pokud sudý řádek, pokuň se nahoru
                if (y > 0)
                    y -= 1;
            }
            else {
            // pokud lichý řádek, posuň se nahoru a doleva
                if (y > 0 && x > 0) {
                    x -= 1;
                    y -= 1;
                }
            }

        }

        else if (instance.keyPressed('S')){
 //           System.out.println("S was pressed");
            if (isEven) {
                // pokud sudý řádek, posuň se nahoru a doprava
                if (y < 6)
                    y += 1;
                    x += 1;
            }
            else {
                // pokud lichý řádek, posuň se nahoru

                 if (y < 6 && x < 6) {
                    y += 1;

                }
            }
        }

        else if (instance.keyPressed('A')){
//            System.out.println("A was pressed");
            if (isEven) {
                // pokud lichý řádek, posuň se dolů a doleva
                if (y < 6)
                y += 1;

            }
            else {
                // pokud sudý řádek, posuň se dolů

                if (y < 6 && x < 6) {
                x -=1;
                y += 1;
                }
            }
        }

        else if (instance.keyPressed('D')){
//            System.out.println("D was pressed");
            if (isEven) {
                // pokud lichý řádek, posuň se dolů
                if (y > 0)
                y -= 1;
                x += 1;
            }
            else {
                // pokud sudý řádek, posuň se dolů a doprava
                if (y > 0 && x > 0) {
                y -= 1;
                }
            }
        }

        tilemap[y * 7 + x] = 1; // změnit číslo na pozici XY na 1


        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                int val = tilemap[y * 7 + x];
                if (val == 1) {
                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;
                    ox += (tile-8) * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4) - tile;

                    se.drawImage(tx, ty, qbrt, scale);
                }
            }
        }
    }
}