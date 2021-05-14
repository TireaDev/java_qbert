package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameScene extends Scene {

    MapScene mapScene;
    EnemyScene enemyScene;
    QbertScene qbertScene;
    GameUIScene gameUIScene;

    static int score = 0;

    @Override
    public void onAwake() {
        mapScene = new MapScene();
        enemyScene = new EnemyScene();
        qbertScene = new QbertScene();
        gameUIScene = new GameUIScene();


        mapScene.onAwake();
        enemyScene.onAwake();
        qbertScene.onAwake();
        gameUIScene.onAwake();
    }

    @Override
    public void onUpdate(float deltaTime) {
        mapScene.onUpdate(deltaTime);
        enemyScene.onUpdate(deltaTime);
        qbertScene.onUpdate(deltaTime);
        gameUIScene.onUpdate(deltaTime);

        if (qbertScene.x == enemyScene.enemyX && qbertScene.y == enemyScene.enemyY) {
            System.out.println("collided");
        }

        if (mousePressed(0)) {
            addScore(25);
        }

        if (mousePressed(1)) {
            System.out.println("score: " + score);
        }
    }

    public static void addScore(int i) {
        score += i;
    }

}
