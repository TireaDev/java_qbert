package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.math.Vec2i;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.MapScene.mapWidth;

public class EntityScene extends Scene{


    byte[][] qbertSprites      = new byte[8][];
    byte[][] redBallSprites    = new byte[8][];
    byte[][] purpleBallSprites = new byte[8][];
    
    public static byte[] tilemap = new byte[7*7];
    
    Entity[] entities = new Entity[3];

    @Override
    public void onAwake() {
    
        for (int ii = 0; ii < 8; ii++) {
            qbertSprites     [ii] = getSubImage(atlas, 16*ii       ,  0, 16, 16);
            redBallSprites   [ii] = getSubImage(atlas, 16*(ii%2)   , 16, 16, 16);
            purpleBallSprites[ii] = getSubImage(atlas, 16*(ii%2)+64, 16, 16, 16);
        }
        
        entities[0] = new Qbert(new Vec2i(2, 3), qbertSprites);
        entities[1] = new Ball(new Vec2i(3, 0), redBallSprites);
        entities[2] = new Ball(new Vec2i(4, 4), purpleBallSprites);
        
        entities[2].update(.3f);
    }

    @Override
    public void onUpdate(float deltaTime) {
    
        tilemap = new byte[7*7];
        
        for (int ii = entities.length-1; ii >= 0; ii--) {
            entities[ii].update(deltaTime);
            tilemap[entities[ii].pos.y * mapWidth + entities[ii].pos.x] = (byte)(ii + 1);
        }
    
    
        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = tilemap[y * mapWidth + x];
                if (val > 0) {
                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;
                    ox += tile/2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4);

                    if (val > entities.length) continue;
                    
                    drawImage(tx+16, ty-8, entities[val-1].sprite, scale);
                }
            }
        }
    }
}

class Qbert extends Entity {

    public Qbert(Vec2i pos, byte[][] sprites) {
        super(pos, sprites);
    }

    @Override
    public void update(float deltaTime) {
    
        dir.x = 0;
        dir.y = 0;
    
        if (keyPressed('W') && pos.y > 0 && pos.x >= 0){
            dir.x = -1;
            dir.y = -1;
        }else if (keyPressed('S') && pos.y < 6 && pos.x < 6){
            dir.x = 1;
            dir.y = 1;
        }else if (keyPressed('A') && pos.y < 6 && pos.x >= 0){
            dir.x = -1;
            dir.y = 1;
        }else if (keyPressed('D') && pos.y > 0 && pos.x < 6){
            dir.x = 1;
            dir.y = -1;
        }
        
        move();
    }
    
    @Override
    public void spawn(){
        pos.x = 3;
        pos.y = 0;
        
        dir.x = 0;
        dir.y = 0;
    }
}

class Ball extends Entity {

    float delay = 0;

    public Ball(Vec2i pos, byte[][] sprites) {
        super(pos, sprites);
    }

    @Override
    public void update(float deltaTime) {
    
        dir.x = 0;
        dir.y = 0;
    
        if (delay < 1) {
            delay += deltaTime;
            return;
        }
        
        if (pos.y < 6) {
            dir.x = (int)(Math.random()*2)-1;
            dir.y = 1;
        } else {
            spawn();
        }
        
        delay = 0;
        
        move();
    }
    
    @Override
    public void spawn(){
        pos.x = 3;
        pos.y = 0;
        
        dir.x = 0;
        dir.y = 0;
        
        delay = 0;
    }
}