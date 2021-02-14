package com.tireadev.qbert;

import com.tireadev.shadowengine.ShadowEngine;

public class Main extends ShadowEngine {

    final int s = 2;

    byte[] block_sides;
    byte[] block_top;

    @Override
    public void onAwake() {
        block_sides = loadImage("/textures/block/block-sides.png");
        block_top = loadImage("/textures/block/block-top.png");
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onUpdate(float v) {

        if (keyPressed(256)) close();

        clear(BLACK);

        drawImage(width/2-16*s, height/2-16*s, 32, block_sides, s);
        drawImage(width/2-16*s, height/2-16*s, 32, block_top, s);
    }

    @Override
    public void onClose() {

    }



    public static void main(String[] args) {
        Main main = new Main();
        if (main.construct(512, 480, "Q*Bert", true, false))
            main.start();
    }
}
