package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class MapScene extends Scene {

    byte[][] blocks = new byte[4][];

    static final byte mapWidth = 7;
    static byte[] map;

    public static void changeTileTo(int x, int y, byte v) {
        map[y * mapWidth + x] = v;
    }

    public static void setMapTo(byte v) {
        map = new byte[] {
            0,0,0,v,0,0,0,
             0,0,v,v,0,0,0,
            0,0,v,v,v,0,0,
             0,v,v,v,v,0,0,
            0,v,v,v,v,v,0,
             v,v,v,v,v,v,0,
            v,v,v,v,v,v,v
        };
    }

    @Override
    public void onAwake() {
    
        setMapTo((byte)1);
    
        blocks[1] = getSubImage(atlas, 0, 5 * 32, 32, 32);
        blocks[2] = getSubImage(atlas, 0, 6 * 32, 32, 32);
        blocks[3] = getSubImage(atlas, 0, 7 * 32, 32, 32);
        blocks[0] = getSubImage(atlas, 0, 9 * 32, 32, 32);
    }

    @Override
    public void onUpdate(float deltaTime) {

        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = map[y * mapWidth + x];
                if (val > 0) {

                    int ox = 0, oy = tile * 3 / 4 * scale;
                    if (y % 2 == 1)
                        ox = tile / 2 * scale;

                    ox += tile / 2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5 / 4);

                    if (val >= blocks.length)
                        val = 0;
                    drawImage(tx, ty, blocks[val], scale);
                }
            }
        }
    }
}