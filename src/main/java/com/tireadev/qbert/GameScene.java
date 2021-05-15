package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameScene extends Scene {

    MapScene mapScene;
    GameUIScene gameUIScene;
    EntityScene entityScene;

    static int score = 0;

    @Override
    public void onAwake() {
        mapScene = new MapScene();
        gameUIScene = new GameUIScene();
        entityScene = new EntityScene();


        mapScene.onAwake();
        gameUIScene.onAwake();
        entityScene.onAwake();
    }

    @Override
    public void onUpdate(float deltaTime) {
        mapScene.onUpdate(deltaTime);
        gameUIScene.onUpdate(deltaTime);
        entityScene.onUpdate(deltaTime);

        if (
                entityScene.entities[0].pos.x == entityScene.entities[1].pos.x
             && entityScene.entities[0].pos.y == entityScene.entities[1].pos.y) {
            System.out.println("collided");
            entityScene.entities[0].spawn();
            gameUIScene.livesNum--;
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
