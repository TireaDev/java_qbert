package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    MainMenuScene mainMenuScene;
    GameOverScene gameOverScene;

    static final byte scale = 2, tile = 32;

    static byte[] atlas;

    GameScene gameScene;

    public static final int KEY_ENTER = 257;
    public static final int KEY_UP    = 262;
    public static final int KEY_DOWN  = 263;
    public static final int KEY_LEFT  = 264;
    public static final int KEY_RIGHT = 265;

    @Override
    public void onAwake() {
        atlas = loadImage("src/main/resources/textures/atlas.png");

        mainMenuScene = new MainMenuScene();
        gameOverScene = new GameOverScene();
        gameScene = new GameScene();

        mainMenuScene.onAwake();
        gameOverScene.onAwake();
        gameScene.onAwake();
    }

    @Override
    public void onStart() {
        // gameScene.setActive();
        mainMenuScene.setActive();
    }

    @Override
    public void onUpdate(float deltaTime) {


        clear(BLACK);

        Scene.active.onUpdate(deltaTime);

        if (keyPressed('B')) gameOverScene.setActive();
        if (keyPressed('M')) mainMenuScene.setActive();
        
        if (
                keyPressed(KEY_ENTER)
                && (mainMenuScene.cursorPosition == 0)
                && Scene.active.equals(mainMenuScene)
        ) gameScene.setActive();
        else if (
                keyPressed(KEY_ENTER)
                && (mainMenuScene.cursorPosition == 1)
                && Scene.active.equals(mainMenuScene)
        ) close();

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
