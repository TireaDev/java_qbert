package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.GameUIScene.*;

public class GameScene extends Scene {

    MapScene mapScene;
    GameUIScene gameUIScene;
    EntityScene entityScene;

    static int score = 0;
    static byte changeTo = 2;
    static byte changeBy = 1;
    
    static boolean newRound = false;

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
        
        for (int ii = 1; ii < entityScene.entities.size(); ii++) {
            if (
                    entityScene.entities.get(0).pos.x == entityScene.entities.get(ii).pos.x
                 && entityScene.entities.get(0).pos.y == entityScene.entities.get(ii).pos.y
            ) {
                System.out.println("collided with " + ii);
                entityScene.entities.get(0).spawn();
                GameUIScene.livesNum--;
                return;
            }
        }
        
        if (EntityScene.qbertJumped && !newRound) {
            if (MapScene.map[entityScene.entities.get(0).pos.x + MapScene.mapWidth * entityScene.entities.get(0).pos.y] != changeTo)
                GameUIScene.score += 25;
            MapScene.changeTileTo(entityScene.entities.get(0).pos.x, entityScene.entities.get(0).pos.y, (byte)1, changeTo);
        }
        
        newRound = false;
        
        if (isCompleted()) {
            System.out.println("Round " + (roundNum) + " Level " + (levelNum) + " Completed");
            
            changeTo += changeBy;
            roundNum += 1;
            
            if (roundNum > 4) {
                roundNum = 1;
                levelNum += 1;
                changeBy += 1;
                changeTo += 1;
            }
            
            cubesVal = (changeTo - 1) % 4;
            
            entityScene.entities.get(0).spawn();
            entityScene.clearEnemies();
            newRound = true;
        }
    }
    
    public boolean isCompleted() {
        for (int ii = 0; ii < MapScene.mapWidth*MapScene.mapWidth - 1; ii++) {
            byte val = MapScene.map[ii];
            if (val == (byte)0) continue;
            if (val != (byte)changeTo) return false;
        }
        return true;
    }
}
