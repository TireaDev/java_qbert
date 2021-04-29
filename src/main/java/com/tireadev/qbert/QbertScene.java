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

    boolean isEven;

    @Override
    public void onUpdate(float v) {

        // x: 0 → w
        // y: 0 ↓ h

        tilemap[y * 7 + x] = 0;

        isEven = (y * 7 + 1) % 2 == 0;

        if (instance.keyPressed('W')){
            if (isEven) {
                // nahoru
                if (y > 0) y -= 1;
            }
            else {
                // doleva
                if (y > 0 && x > 0) x -= 1;
                // nahoru
                if (y > 0) y -= 1;
            }
        }

        else if (instance.keyPressed('S')){
            if (isEven) {
                // doprava
                if (y < 6 && x < 6) x += 1;
                // dolů
                if (y < 6 ) y += 1;
            }
            else {
                // dolů
                 if (y < 6) y += 1;
            }
        }

        else if (instance.keyPressed('A')){
            if (isEven) {
                // dolů
                if (y < 6) y += 1;
            }
            else {
                // doleva
                if (y < 6 && x > 0) x -= 1;
                // dolů
                if (y < 6) y += 1;
            }
        }

        else if (instance.keyPressed('D')){
            if (isEven) {
                // doprava
                if (y > 0 && x < 6) x += 1;
                // nahoru
                if (y > 0) y -= 1;
            }
            else {
                // nahoru
                if (y > 0) y -= 1;
            }
        }

        tilemap[y * 7 + x] = 1;

        System.out.println("x:" + x + "; y:" + y);

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