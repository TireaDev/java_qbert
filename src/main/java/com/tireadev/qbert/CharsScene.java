package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.Main.scale;

public class CharsScene extends Scene{

    ShadowEngine se;

    final int qbtStartX = 2;
    final int qbtStartY = 3;
    final int enemyStartX = 3;
    final int enemyStartY = 0;

    int qbtX = qbtStartX;
    int qbtY = qbtStartY;
    int enemyX = enemyStartX;
    int enemyY = enemyStartY;

    boolean qbtIsEven;
    boolean enemyIsEven;

    int enemyDirection; // 0 = left, 1 = right
    int delay = 0;

    byte[][] character = new byte[3][];

    public static final byte[] tilemap = new byte[] {
            0,0,0,2,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
    };

    public CharsScene(ShadowEngine instance) {
        super(instance);
        this.se = this.instance;
    }

    public void resetEnemy(){
        enemyX = enemyStartX;
        enemyY = enemyStartY;
    }

    public void respawnQbt(){
        qbtX = qbtStartX;
        qbtY = qbtStartY;
    }

    @Override
    public void onAwake() {
        character[1] = se.getSubImage(atlas, 5*16, 0, 16, 16);
        character[2] = se.getSubImage(atlas, 5*16, 1*16, 16, 16);
    }

    @Override
    public void onUpdate(float v) {

        tilemap[qbtY * MapScene.mapWidth + qbtX] = 0;

        qbtIsEven = (qbtY * MapScene.mapWidth + 1) % 2 == 0;
        enemyIsEven = (enemyY * MapScene.mapWidth + 1) % 2 == 0;

        //Qbert//

        if (instance.keyPressed('W')){
            if (qbtIsEven) {
                // nahoru
                if (qbtY > 0) qbtY -= 1;
            }
            else {
                // doleva
                if (qbtY > 0 && qbtX > 0) qbtX -= 1;
                // nahoru
                if (qbtY > 0) qbtY -= 1;
            }
        }

        else if (instance.keyPressed('S')){
            if (qbtIsEven) {
                // doprava
                if (qbtY < 6 && qbtX < 6) qbtX += 1;
                // dolů
                if (qbtY < 6 ) qbtY += 1;
            }
            else {
                // dolů
                if (qbtY < 6) qbtY += 1;
            }
        }

        else if (instance.keyPressed('A')){
            if (qbtIsEven) {
                // dolů
                if (qbtY < 6) qbtY += 1;
            }
            else {
                // doleva
                if (qbtY < 6 && qbtX > 0) qbtX -= 1;
                // dolů
                if (qbtY < 6) qbtY += 1;
            }
        }

        else if (instance.keyPressed('D')){
            if (qbtIsEven) {
                // doprava
                if (qbtY > 0 && qbtX < 6) qbtX += 1;
                // nahoru
                if (qbtY > 0) qbtY -= 1;
            }
            else {
                // nahoru
                if (qbtY > 0) qbtY -= 1;
            }
        }

        //Enemy//

        if(delay == 60){
            tilemap[enemyY * MapScene.mapWidth + enemyX] = 0;
            if(enemyY == 6){
                resetEnemy();
            }else{
                enemyDirection = (int)(Math.random()*2);
                if(enemyDirection == 1){ // 1 = right, 0 = left
                    enemyY++;
                    if(enemyIsEven){
                        enemyX++;
                    }
                }else{
                    enemyY++;
                    if(!enemyIsEven){
                        enemyX--;
                    }
                }
            }
            tilemap[enemyY * MapScene.mapWidth + enemyX] = 2;
            delay = 0;
        }else {
            delay++;
        }

        tilemap[qbtY * MapScene.mapWidth + qbtX] = 1;

        for (int y = 0; y < MapScene.mapWidth; y++) {
            for (int x = 0; x < MapScene.mapWidth; x++) {
                int val = tilemap[y * MapScene.mapWidth + x];
                if (val > 0) {
                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;
                    ox += tile/2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4);

                    if (val >= character.length) val = 0;
                    se.drawImage(tx+16, ty-8, character[val], scale);
                }
            }
        }
    }

}
