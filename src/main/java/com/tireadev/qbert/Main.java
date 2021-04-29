package com.tireadev.qbert;

import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    static final byte scale = 4, tile = 32;
    static byte[] atlas;

    GameScene gameScene;

    @Override
    public void onAwake() {
        atlas = loadImage("src/main/resources/textures/atlas.png");

        gameScene = new GameScene(this);

        gameScene.onAwake();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float deltaTime) {

        if (keyPressed(256)) close();

        clear(BLACK);

        gameScene.onUpdate(deltaTime);
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


