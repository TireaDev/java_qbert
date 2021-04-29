package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;
import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import static com.tireadev.qbert.Main.atlas;

public class Main extends ShadowEngine {

    static final byte scale = 2, tile = 32;
    static final String path_prefix = "src/main/resources/";
    static final String path_atlas = path_prefix + "textures/atlas.png";
    static byte[] atlas;

    EnemyScene en;

    QbertScene qbt;

    @Override
    public void onAwake() {
        atlas = loadImage(path_atlas);

        new MapScene(this).setActive();
        en = new EnemyScene(this);
        Scene.active.onAwake();
        en.onAwake();

       new MapScene(this).setActive();
        qbt = new QbertScene(this);
        Scene.active.onAwake();
        qbt.onAwake();

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


