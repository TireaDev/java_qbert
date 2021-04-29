package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.scale;
import static com.tireadev.qbert.Main.atlas;

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





