package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    MainMenuScene mainMenuScene;
    GameOverScene gameOverScene;

    static final byte scale = 2, tile = 32;

    static byte[] atlas;

    Scene gameScene;

    @Override
    public void onAwake() {
        atlas = loadImage("src/main/resources/textures/atlas.png");

        mainMenuScene = new MainMenuScene(this);
        gameOverScene = new GameOverScene(this);
        gameScene = new GameScene(this);
        
        mainMenuScene.onAwake();
        gameOverScene.onAwake();
        gameScene.onAwake();
    }

    @Override
    public void onStart() {
        gameScene.setActive();
    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

        clear(BLACK);

        Scene.active.onUpdate(deltaTime);

        if (keyPressed('B')) gameOverScene.setActive();
        if (keyPressed('M')) mainMenuScene.setActive();
        if (keyPressed('N')) gameScene.setActive();

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
