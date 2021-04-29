package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import java.util.Locale;

import static com.tireadev.qbert.Main.*;

public class Main extends ShadowEngine {

    MainMenuScene mainMenu;
    GameOverScene gameOver;

    static final byte scale = 2, tile = 32;
    static final String path_prefix = "src/main/resources/";
    static final String path_atlas = path_prefix + "textures/atlas.png";
    static byte[] atlas;


    @Override
    public void onAwake() {

        atlas = loadImage(path_atlas);

        gameOver = new GameOverScene(this);
        mainMenu = new MainMenuScene(this);

        mainMenu.onAwake();
        gameOver.onAwake();


    //    new MapScene(this).setActive();
        mainMenu.setActive();
        //Scene.active.onAwake();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

        clear(BLACK);

        Scene.active.onUpdate(deltaTime);

        if (keyPressed('A')) gameOver.setActive();
        if (keyPressed('D')) mainMenu.setActive();

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


    public MainMenuScene(ShadowEngine instance) {
        super(instance);
    }

    byte[] ultraGames;
    byte[] qbertTitle;
    byte[] bertHimself;
    byte[][] chars = new byte['Z'-44+1][];
    //byte[][] nums = new byte[10][];


    public void onAwake() {
        se = instance;
        ultraGames = se.getSubImage(atlas,344,0,8*20,8*4);
        qbertTitle = se.getSubImage(atlas,344-8,32,16*10,16*3);
        for (int i = '0'-44; i <= '9'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'0'+44),64,8,8); // řádek 1
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'A'+44),64+8,8,8); // řádek 2
        chars[0] = se.getSubImage(atlas, 128+160, 64, 8, 8); // ,
        chars[2] = se.getSubImage(atlas, 128+168, 64, 8, 8); // .
        bertHimself = se.getSubImage(atlas,0,0,16,16);
    }


    @Override
    public void onUpdate(float deltaTime){
        se.drawImage((5*16+8)*scale,(32-8)*scale,ultraGames,scale);
        se.drawImage((3*16)*scale,(16*3)*scale,qbertTitle,scale);
        se.drawText("Play Select", (16*6-8)*scale, (16*6)*scale, chars, 44, scale, true);
        se.drawText("Play", (16*7)*scale, (16*7)*scale, chars, 44, scale, true);
        se.drawText("Quit", (16*7)*scale, (16*8)*scale, chars, 44, scale, true);
        se.drawImage((5*16)*scale,(16*7)*scale,bertHimself,scale);
        se.drawText("TM AND  0  1989", 5*16*scale, 10*16*scale, chars, 44, scale, true);
        se.drawText("KONAMI INDUSTRY CO.,LTD.", 2*16*scale, (10*16*scale)+16, chars, 44, scale, true);
        se.drawText("LICENSED BY", 6*16*scale, 11*16*scale, chars, 44, scale, true);
        se.drawText("NINTENDO OF AMERICA INC.", 2*16*scale, (11*16*scale)+16, chars, 44, scale, true);
        se.drawText("ULTRA GAMES IS A REGISTERED ", 16*scale, (12*16*scale)+16, chars, 44, scale, true);
        se.drawText("TRADEMARK OF ULTRA SOFTWARE", 16*scale, 13*16*scale, chars, 44, scale, true);
        se.drawText("CORPORATION.", 16*scale, (13*16*scale)+16, chars, 44, scale, true);




        //byte[][] chars = new byte['Z'-44+1][];

        //for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = se.loadImage(path_atlas,128+8*(i-'A'+44),64+8,8,8);

        //se.drawText("GAME OVER", (16*6-8)*scale, (16*8)*scale, chars, 44, scale, true);
        //se.drawText("Continue A", (16*7)*scale, (16*9)*scale, chars, 44, scale, true);
        //se.drawText("Game end B", (16*7)*scale, (16*10)*scale, chars, 44, scale, true);
    }
}

class GameOverScene extends Scene{
    ShadowEngine se;

    public GameOverScene(ShadowEngine instance) {
        super(instance);
    }

    byte[][] chars = new byte['Z'-44+1][];

    @Override
    public void onAwake() {
        se = instance;

        chars[0] = se.getSubImage(atlas, 0, 0, 8, 8);
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'A'+44),64+8,8,8);
    }

    @Override
    public void onUpdate(float v) {
        se.drawText("GAME  OVER", (16*5)*scale, (16*6)*scale, chars, 44, scale, true);
        se.drawText("Continue A", (16*5)*scale, (16*8)*scale, chars, 44, scale, true);
        se.drawText("Game end B", (16*5)*scale, (16*9)*scale, chars, 44, scale, true);
    }
}
