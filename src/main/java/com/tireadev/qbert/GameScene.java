package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

public class GameScene extends Scene {

    Scene mapScene, enemyScene, qbertScene;



    static int score = 0;

    public GameScene(ShadowEngine instance) {
        super(instance);
    }

    @Override
    public void onAwake() {
        mapScene = new MapScene(instance);
        enemyScene = new EnemyScene(instance);
        qbertScene = new QbertScene(instance);

        mapScene.onAwake();
        enemyScene.onAwake();
        qbertScene.onAwake();

    }

    @Override
    public void onUpdate(float deltaTime) {
        mapScene.onUpdate(deltaTime);
        enemyScene.onUpdate(deltaTime);
        qbertScene.onUpdate(deltaTime);

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
