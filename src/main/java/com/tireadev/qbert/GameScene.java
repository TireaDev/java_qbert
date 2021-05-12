package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class GameScene extends Scene {

    MapScene mapScene;
    CharsScene charsScene;

    static int score = 0;
    boolean isDead;

    public GameScene(ShadowEngine instance) {
        super(instance);
    }

    @Override
    public void onAwake() {
        mapScene = new MapScene(instance);
        charsScene = new CharsScene(instance);

        mapScene.onAwake();
        charsScene.onAwake();

        isDead = false;
    }

    @Override
    public void onUpdate(float deltaTime) {
        mapScene.onUpdate(deltaTime);
        charsScene.onUpdate(deltaTime);

        if (charsScene.qbtX == charsScene.enemyX && charsScene.qbtY == charsScene.enemyY) {
            isDead = true;
        }

        if(instance.mousePressed(0)){
           addScore(25);
        }

        if(instance.mousePressed(1)) {
            System.out.println("score: " + score);
        }
    }

    public static void addScore(int i){
        score += i;
    }

}
