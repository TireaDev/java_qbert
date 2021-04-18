package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.path_atlas;
import static com.tireadev.qbert.Main.path_prefix;

public class Main extends ShadowEngine {

    static final byte scale = 2, tile = 32;
    static final String path_prefix = "src/main/resources/";
    static final String path_atlas = path_prefix + "textures/atlas.png";

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

    byte[][] blocks = new byte[4][];

    final byte mapWidth = 7;
    final byte[] map = new byte[] {
            0,0,0,1,0,0,0,
             0,0,1,1,0,0,0,
            0,0,1,1,1,0,0,
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
        blocks[1] = se.loadImage(path_atlas, 0, 5*32, 32, 32);
        blocks[2] = se.loadImage(path_atlas, 0, 6*32, 32, 32);
        blocks[3] = se.loadImage(path_atlas, 0, 7*32, 32, 32);
        blocks[0] = se.loadImage(path_atlas, 0, 9*32, 32, 32);
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

                    if (val >= blocks.length) val = 0;
                    se.drawImage(tx, ty, blocks[val], scale);
                }
            }
        }
    }
}
