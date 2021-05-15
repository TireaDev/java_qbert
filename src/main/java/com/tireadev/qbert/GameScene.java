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
        
        for (int ii = 1; ii < entityScene.entities.length; ii++) {
            if (
                    entityScene.entities[0].pos.x == entityScene.entities[ii].pos.x
                 && entityScene.entities[0].pos.y == entityScene.entities[ii].pos.y
            ) {
                System.out.println("collided");
                entityScene.entities[0].spawn();
                gameUIScene.livesNum--;
                return;
            }
        }

        if (mousePressed(0)) {
            addScore(25, gameUIScene);
        }
    }

    public static void addScore(int i, GameUIScene guis) {
        guis.score += i;
    }
}
