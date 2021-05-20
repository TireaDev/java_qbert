package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class MapScene extends Scene {

    byte[][] blocks = new byte[5][];

    static final byte mapWidth = 7;
    static byte[] map;

    public static void changeTileTo(int x, int y, byte s, byte v) {
        if (map[y * mapWidth + x] < v)
            map[y * mapWidth + x] += s;
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
    
        blocks[1] = getSubImage(atlas, 0, 9 * 32, 32, 32);
        blocks[2] = getSubImage(atlas, 0, 5 * 32, 32, 32);
        blocks[3] = getSubImage(atlas, 0, 6 * 32, 32, 32);
        blocks[4] = getSubImage(atlas, 0, 7 * 32, 32, 32);
    }

    @Override
    public void onUpdate(float deltaTime) {

        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = map[y * mapWidth + x] % 4 + 1;
                if (map[y * mapWidth + x] > 0) {

                    int ox = 0, oy = tile * 3 / 4 * scale;
                    if (y % 2 == 1)
                        ox = tile / 2 * scale;

                    ox += tile / 2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5 / 4);

                    drawImage(tx, ty, blocks[val], scale);
                }
            }
        }
    }
}