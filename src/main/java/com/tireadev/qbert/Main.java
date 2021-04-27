package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.path_atlas;
import static com.tireadev.qbert.Main.path_prefix;
import static com.tireadev.shadowengine.ShadowEngine.*;

public class Main extends ShadowEngine {

    MainMenuScene mainMenu;

    static final byte scale = 2, tile = 32;
    static final String path_prefix = "src/main/resources/";
    static final String path_atlas = path_prefix + "textures/atlas.png";

    @Override
    public void onAwake() {

        mainMenu = new MainMenuScene(this);

    //    new MapScene(this).setActive();
        mainMenu.setActive();
        Scene.active.onAwake(); //

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

    //    clear(BLACK);

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

class MainMenuScene extends Scene{

      ShadowEngine se;
      byte[][] chars = new byte[26][];


    public MainMenuScene(ShadowEngine instance) {
        super(instance);
    }

     public void onAwake(){
        se = instance;
         for (int i = 0; i < 26; i++) {chars [i] = se.loadImage(path_atlas,128+8*i,8,8,8);

         }

     }

    @Override
    public void onUpdate(float deltaTime) {
        se.clear(BLACK);
        //for (int i = 0; i < 26; i++) {se.drawImage(i*8*2,i,chars[i],2);

//        }
        se.drawImage(0*8*9,10,chars['P'-65],9);
        se.drawImage(1*8*9,20,chars['A'-65],9);
        se.drawImage(2*8*9,30,chars['V'-65],9);
        se.drawImage(3*8*9,40,chars['E'-65],9);
        se.drawImage(4*8*9,50,chars['L'-65],9);
    }
}