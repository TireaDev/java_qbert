package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    static MainMenuScene mainMenuScene;
    static GameOverScene gameOverScene;
    static WinScene winScene;

    static final byte scale = 2, tile = 16;

    static byte[] atlas;
    static byte[][] white_font = new byte['Z' - 44+1][];
    static int white_font_offset = 44;
    
    static boolean paused = false;

    GameScene gameScene;

    public static final int KEY_ESC   = 256;
    public static final int KEY_ENTER = 257;
    public static final int KEY_RIGHT = 262;
    public static final int KEY_LEFT  = 263;
    public static final int KEY_DOWN  = 264;
    public static final int KEY_UP    = 265;

    @Override
    public void onAwake() {
        atlas = loadImage("src/main/resources/textures/atlas.png");

        for (int i = '0' - 44; i <= '9' - 44; i++)
            white_font[i] = getSubImage(atlas, 128 + 8 * (i - '0' + 44), 64, 8, 8);     // řádek 1
        for (int i = 'A' - 44; i <= 'Z' - 44; i++)
            white_font[i] = getSubImage(atlas, 128 + 8 * (i - 'A' + 44), 64 + 8, 8, 8); // řádek 2
        white_font[0] = getSubImage(atlas, 128 + 80, 64, 8, 8);                         // ,
        white_font[2] = getSubImage(atlas, 128 + 88, 64, 8, 8);                         // .
        white_font['@'-44] = getSubImage(atlas, 128 + 96, 64, 8, 8);                    // @

        mainMenuScene = new MainMenuScene();
        gameOverScene = new GameOverScene();
        gameScene = new GameScene();
        winScene = new WinScene();
        mainMenuScene.onAwake();
        gameOverScene.onAwake();
        gameScene.onAwake();
        winScene.onAwake();
    }

    @Override
    public void onStart() {
        mainMenuScene.setActive();
    }

    @Override
    public void onUpdate(float deltaTime) {
        
        if (keyPressed(KEY_ESC) && Scene.active.equals(gameScene))
            paused = !paused;
        
        if (!paused) {
            clear(BLACK);
            Scene.active.onUpdate(deltaTime);
            
            if (keyPressed('N')) {
                winScene.onAwake();
                winScene.setActive();
            }
            if (keyPressed('M')) {
                gameOverScene.onAwake();
                gameOverScene.setActive();
            }

            if (keyPressed(KEY_ENTER)) {
                if (Scene.active.equals(mainMenuScene)) {
                    if (mainMenuScene.cursorPosition == 0) {
                        gameScene.onAwake();
                        gameScene.setActive();
                    } else if (mainMenuScene.cursorPosition == 1) {
                        close();
                    }
                } else if (Scene.active.equals(gameOverScene)) {
                    if ((gameOverScene.cursorPosition == 0)) {
                        mainMenuScene.setActive();
                    } else if (gameOverScene.cursorPosition == 1) {
                        close();
                    }
                } else if (Scene.active.equals(winScene)) {
                    if ((winScene.cursorPosition == 0)) {
                        mainMenuScene.setActive();
                    } else if (winScene.cursorPosition == 1) {
                        close();
                    }
                }
            }


        } else {
            fillRect((7*tile - tile/4) * scale, (tile*7) * scale, (tile*2 + tile/2 + 1) * scale, (tile/2) * scale, BLACK);
            drawText("PAUSE", (7*tile - tile/4) * scale, (tile*7) * scale, white_font, white_font_offset, scale, true);
        }
    }

    @Override
    public void onClose() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        if (main.construct(256 * scale, 240 * scale, "Q*Bert", true, false))
            main.start();
    }
}
