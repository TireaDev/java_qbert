package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;
import com.tireadev.shadowengine.math.Vec2i;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.Main.scale;
import static com.tireadev.shadowengine.ShadowEngine.instance;

public class EntityScene extends Scene{

    Vec2i qPos = new Vec2i(2, 3);
    Vec2i ePos = new Vec2i(3, 0);
    Vec2i dir = new Vec2i();

    boolean isEven;
    int delay = 0;
    int[] enemyDirection = {-1, 1};
    int x, y;

    byte[][] entity = new byte[3][];

    public static final byte[] tilemap = new byte[] {
            0,0,0,2,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
             0,0,0,0,0,0,0,
            0,0,0,0,0,0,0
    };

    public void resetEnemy(){
        ePos.x = 3;
        ePos.y = 0;
    }

    public void respawnQbt(){
        qPos.x = 2;
        qPos.y = 3;
    }

    void move(Vec2i pos, Vec2i dir){
        isEven = (pos.y * MapScene.mapWidth + 1) % 2 == 0;

        y = pos.y + dir.y;
        if(isEven && dir.x == 1 || !isEven && dir.x == -1){
            x = pos.x + dir.x;
        }else{
            x = pos.x;
        }

        if(MapScene.map[y * MapScene.mapWidth + x] > 0){
            pos.x = x;
            pos.y = y;
        }
    }

    @Override
    public void onAwake() {
        entity[1] = getSubImage(atlas, 5*16, 0, 16, 16);
        entity[2] = getSubImage(atlas, 5*16, 1*16, 16, 16);
    }

    @Override
    public void onUpdate(float v) {

        //Qbert//

        tilemap[qPos.y * MapScene.mapWidth + qPos.x] = 0;

        if (instance.keyPressed('W') && qPos.y > 0 && qPos.x >= 0){
            dir.x = -1;
            dir.y = -1;
            move(qPos, dir);
        }else if (instance.keyPressed('S') && qPos.y < 6 && qPos.x < MapScene.mapWidth){
            dir.x = 1;
            dir.y = 1;
            move(qPos, dir);
        }else if (instance.keyPressed('A') && qPos.y < 6 && qPos.x >= 0){
            dir.x = -1;
            dir.y = 1;
            move(qPos, dir);
        }else if (instance.keyPressed('D') && qPos.y > 0 && qPos.x < MapScene.mapWidth){
            dir.x = 1;
            dir.y = -1;
            move(qPos, dir);
        }

        tilemap[qPos.y * MapScene.mapWidth + qPos.x] = 1;

        //Enemy//

        if(delay == 60){
            tilemap[ePos.y * MapScene.mapWidth + ePos.x] = 0;
            if(ePos.y == 6){
                resetEnemy();
            }else{
                dir.x = enemyDirection[(int)(Math.random()*2)];
                dir.y = 1;
                move(ePos, dir);
            }
            tilemap[ePos.y * MapScene.mapWidth + ePos.x] = 2;
            delay = 0;
        }else {
            delay++;
        }

        for (int y = 0; y < MapScene.mapWidth; y++) {
            for (int x = 0; x < MapScene.mapWidth; x++) {
                int val = tilemap[y * MapScene.mapWidth + x];
                if (val > 0) {
                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;
                    ox += tile/2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4);

                    if (val >= entity.length) val = 0;
                    drawImage(tx+16, ty-8, entity[val], scale);
                }
            }
        }

    }
}
