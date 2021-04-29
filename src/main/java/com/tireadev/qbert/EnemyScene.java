package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.Main.scale;

public class EnemyScene extends Scene {

    ShadowEngine se;

    final int enemyStartX = 3;
    final int enemyStartY = 0;
    int enemyX = enemyStartX;
    int enemyY = enemyStartY;
    final int startEo = 0; // 0 = even, 1 = odd
    int eo = startEo;
    int enemyDirection; // 0 = left, 1 = right
    int delay = 0;

    byte[][] enemy = new byte[4][];

    static final byte mapWidth = 7;

    public static final byte[] enemies = new byte[] {
            0,0,0,1,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
    };

    public EnemyScene(ShadowEngine instance) {
        super(instance);
        this.se = this.instance;
    }

    public void resetEnemyXY(){
        this.enemyX = this.enemyStartX;
        this.enemyY = this.enemyStartY;
    }

    public void checkCollisions(){

    }

    @Override
    public void onAwake() {
        enemy[1] = se.getSubImage(atlas, 5*16, 1*16, 16, 16);
        enemy[0] = se.getSubImage(atlas, 6*16, 1*16, 16, 16);
    }

    @Override
    public void onUpdate(float v) {

        if(delay == 60){
            if(enemyY == 6){
                this.enemies[enemyY * 7 + enemyX] = 0;
                resetEnemyXY();
                this.enemies[enemyY * 7 + enemyX] = 1;
            }else{
                this.enemyDirection = (int)(Math.random()*2);
                this.enemies[enemyY * 7 + enemyX] = 0;
                if(enemyDirection == 1){ // 1 = right, 0 = left
                    enemyY++;
                    if(eo == 1){ // 0 = even, 1 = odd
                        enemyX++;
                        eo = 0;
                    }else{
                        eo = 1;
                    }
                }else{
                    enemyY++;
                    if(eo == 0){
                        enemyX--;
                        eo = 1;
                    }else{
                        eo = 0;
                    }
                }

                this.enemies[enemyY * 7 + enemyX] = 1;
            }
            delay = 0;
        }else {
            delay++;
        }

        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = enemies[y * mapWidth + x];
                if (val > 0) {

                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;

                    ox += tile/2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4);

                    if (val >= enemy.length) val = 0;
                    se.drawImage(tx+16, ty-8, enemy[val], scale);
                }
            }
        }
    }
}
