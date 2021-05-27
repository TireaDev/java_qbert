package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.math.Vec2i;

import static com.tireadev.qbert.Main.*;
import static com.tireadev.qbert.MapScene.mapWidth;

import java.util.ArrayList;

import static com.tireadev.qbert.EntityScene.qbertJumped;

public class EntityScene extends Scene{

    byte[][] qbertSprites      = new byte[8][];
    byte[][] redBallSprites    = new byte[8][];
    byte[][] purpleBallSprites = new byte[8][];

    ArrayList<Entity> entities = new ArrayList<Entity>();

    public static byte[] tilemap = new byte[7*7];

    static boolean qbertJumped = false;
    boolean enemySpawned = false;
    int random = (int)(Math.random() * 2) + 60;

    int checker = 0;

    Entity qbert;

    @Override
    public void onAwake() {
        for (int ii = 0; ii < 8; ii++) {
            qbertSprites     [ii] = getSubImage(atlas, 16*ii       ,  0, 16, 16);
            redBallSprites   [ii] = getSubImage(atlas, 16*(ii%2)   , 16, 16, 16);
            purpleBallSprites[ii] = getSubImage(atlas, 16*(ii%2)+64, 16, 16, 16);
        }

        qbert = new Qbert(new Vec2i(2, 3), qbertSprites);

        entities.add(qbert);
    }

    public void clearEnemies() {
        entities.clear();
        enemySpawned = false;
        entities.add(qbert);
    }

    @Override
    public void onUpdate(float deltaTime) {
        tilemap = new byte[7*7];

        //if (keyPressed('P')) entities.add(new Ball(new Vec2i(3, 0), redBallSprites));
        
        if(checker == random){
            entities.add(new Ball(new Vec2i(3, 0), redBallSprites));
            random = (int)(Math.random() * 60 * 10);
            enemySpawned = true;
            checker = 0;
        }else{
            checker++;
        }


        for (int ii = entities.size()-1; ii >= 0; ii--) {
            Entity temp = entities.get(ii);

            temp.update(deltaTime);

            if (temp.type == EntityType.ENEMY && temp.pos.y > 6) {
                entities.remove(ii);
                enemySpawned = false;
                continue;
            }

            tilemap[temp.pos.y * mapWidth + temp.pos.x] = (byte)(ii + 1);
        }

        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = tilemap[y * mapWidth + x];
                if (val > 0) {
                    if (val > entities.size()) continue;

                    byte[] sprite = entities.get(val-1).sprite;

                    int ox = 0;
                    if (y % 2 == 1) ox = tile;
                    ox += tile;

                    int tx = x * tile * 2 + tile / 2 + ox;
                    int ty = y * tile * 3/2 + tile * 21/8 - getImageHeight(sprite);

                    drawImage(tx * scale, ty * scale, sprite, scale);
                }
            }
        }
    }
}

class Qbert extends Entity {

    public Qbert(Vec2i pos, byte[][] sprites) {
        super(pos, sprites, EntityType.PLAYER);
        spawn();
        sprite = sprites[4];
    }

    @Override
    public void update(float deltaTime) {
        dir.x = 0;
        dir.y = 0;

        if (       (keyPressed('W') || keyPressed(KEY_UP))    && pos.y > 0 && pos.x >= 0) {
            dir.x = -1;
            dir.y = -1;
        } else if ((keyPressed('S') || keyPressed(KEY_DOWN))  && pos.y < 6 && pos.x < 6) {
            dir.x = 1;
            dir.y = 1;
        } else if ((keyPressed('A') || keyPressed(KEY_LEFT))  && pos.y < 6 && pos.x >= 0) {
            dir.x = -1;
            dir.y = 1;
        } else if ((keyPressed('D') || keyPressed(KEY_RIGHT)) && pos.y > 0 && pos.x < 6) {
            dir.x = 1;
            dir.y = -1;
        }

        Vec2i prevPos = new Vec2i(pos);

        move();

        qbertJumped = (pos.x != prevPos.x || pos.y != prevPos.y);
    }

    @Override
    public void spawn(){
        pos.x = 3;
        pos.y = 1;

        dir.x = 0;
        dir.y = 0;
    }
}

class Ball extends Entity {

    float delay = 0;

    public Ball(Vec2i pos, byte[][] sprites) {
        super(pos, sprites, EntityType.ENEMY);
        spawn();
    }

    @Override
    public void update(float deltaTime) {
        dir.x = 0;
        dir.y = 0;

        if (delay < 1) {
            delay += deltaTime;
            return;
        }

        dir.x = (int)(Math.random()*2)-1;
        dir.y = 1;

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