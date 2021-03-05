package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    static final byte scale = 2, tile = 32;

    @Override
    public void onAwake() {

        new MapScene(this).setActive();
        Scene.active.onAwake();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

        clear(BLACK);

        Scene.active.onUpdate(deltaTime);
    }

    @Override
    public void onClose() {

    }



    public static void main(String[] args) {
        Main main = new Main();
        if (main.construct(256*scale, 240*scale, "Q*Bert", true, false))
            main.start();
    }
}

class MapScene extends Scene {

    ShadowEngine se;

    final byte scale = Main.scale, tile = Main.tile;

    byte[] block_sides;
    byte[][] block_tops = new byte[4][];

    final byte mapWidth = 7;
    final byte[] map = new byte[] {
            0,0,0,1,0,0,0,
             0,0,1,1,0,0,0,
            0,0,1,2,1,0,0,
             0,1,1,1,1,0,0,
            0,1,1,1,1,1,0,
             1,1,1,1,1,1,0,
            1,1,1,1,1,1,1
    };

    public MapScene(ShadowEngine instance) {
        super(instance);
        this.se = this.instance;
    }

    @Override
    public void onAwake() {
        block_sides = se.loadImage("/textures/block/block-sides.png");
        for (int i = 0; i < block_tops.length; i++) {
            block_tops[i] = se.loadImage("/textures/block/block-top.png", 0, i*16, 32, 16);
        }
    }

    @Override
    public void onUpdate(float deltaTime) {
        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = map[y * mapWidth + x];
                if (val > 0) {

                    int ox = 0, oy = tile*3/4 * scale;
                    if (y % 2 == 1) ox = tile/2 * scale;

                    ox += tile/2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5/4);

                    if (val >= block_tops.length) val = 0;
                    se.drawImage(tx, ty, tile, block_sides, scale);
                    se.drawImage(tx, ty, tile, block_tops[val], scale);
                }
            }
        }
    }
}
