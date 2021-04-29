package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;

public class Main extends ShadowEngine {

    static final byte scale = 2, tile = 32;
    static final String path_prefix = "src/main/resources/";
    static final String path_atlas = path_prefix + "textures/atlas.png";
    static byte[] atlas;

    EnemyScene en;

    @Override
    public void onAwake() {
        atlas = loadImage(path_atlas);

        new MapScene(this).setActive();
        en = new EnemyScene(this);
        Scene.active.onAwake();
        en.onAwake();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

        clear(BLACK);

        Scene.active.onUpdate(deltaTime);

        en.onUpdate(deltaTime);

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
        blocks[1] = se.getSubImage(atlas, 0, 5*32, 32, 32);
        blocks[2] = se.getSubImage(atlas, 0, 6*32, 32, 32);
        blocks[3] = se.getSubImage(atlas, 0, 7*32, 32, 32);
        blocks[0] = se.getSubImage(atlas, 0, 9*32, 32, 32);
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

class EnemyScene extends Scene{

    ShadowEngine se;

    final int enemyStartX = 7 * 32 + 16;
    final int enemyStartY = 3 * 32 - 24;
    int enemyX = enemyStartX;
    int enemyY = enemyStartY;
    int xDirection;
    byte[][] enemies = new byte[4][];
    final int speed = 60;
    int timer = 0;
    final int rows = 7;
    int verticalPosition = 1;
    int waitTime = (int)(Math.random() * 4) + 2;
    int wait = 0;

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
        enemies[0] = se.getSubImage(atlas, 5*16, 16, 16, 16);
    }

    @Override
    public void onUpdate(float v) {
        if(timer == speed){
            if(wait == waitTime){
                if(verticalPosition != rows){
                    this.xDirection = (int)(Math.random()*2);
                    switch(xDirection){
                        case 0: this.enemyX = this.enemyX + Main.tile;
                            break;
                        case 1: this.enemyX = this.enemyX - Main.tile;
                    }

                    this.enemyY = this.enemyY + Main.tile + (Main.tile / Main.scale);
                    verticalPosition++;

                }else{
                    resetEnemyXY();
                    verticalPosition = 1;
                    wait = 0;
                    waitTime = (int)(Math.random() * 2) + 2;

                }
            }else{
                wait++;
            }
            timer = 0;

        }else{
            timer++;
        }

        if(wait == waitTime){
            se.drawImage(this.enemyX, this.enemyY, enemies[0], Main.scale);
        }
    }
}