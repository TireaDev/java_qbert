package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameScene extends Scene {

    MapScene mapScene;
    GameUIScene gameUIScene;
    EntityScene entityScene;

    static int score;
    static byte changeTo;
    static byte changeBy;
    
    static boolean newRound;

    @Override
    public void onAwake() {
        mapScene = new MapScene();
        gameUIScene = new GameUIScene();
        entityScene = new EntityScene();

        mapScene.onAwake();
        gameUIScene.onAwake();
        entityScene.onAwake();
        
        changeTo = 2;
        changeBy = 1;
        score = 0;
        newRound = false;
    }

    @Override
    public void onUpdate(float deltaTime) {
        mapScene.onUpdate(deltaTime);
        gameUIScene.onUpdate(deltaTime);
        entityScene.onUpdate(deltaTime);

        byte[] qberthit;
        byte[] roundup;
        //byte[] levelcomplete;
        qberthit = loadSound("src/main/resources/sound_effects/qbert_fall.wav");
        roundup = loadSound("src/main/resources/music/start_level.wav");
        //levelcomplete = loadSound("src/main/resources/sound_effects/prize.wav");

        for (int ii = 1; ii < entityScene.entities.size(); ii++) {
            if (
                    entityScene.entities.get(0).pos.x == entityScene.entities.get(ii).pos.x
                 && entityScene.entities.get(0).pos.y == entityScene.entities.get(ii).pos.y
            ) {
                System.out.println("collided with " + ii);
                playSound(qberthit, false);
                entityScene.clearEnemies();
                entityScene.entities.get(0).spawn();
                GameUIScene.livesNum--;
                if (GameUIScene.livesNum < 0) {
                    gameOverScene.onAwake();
                    gameOverScene.setActive();
                }
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
            System.out.println("Round " + (GameUIScene.roundNum) + " Level " + (GameUIScene.levelNum) + " Completed");
            playSound(roundup, false);
            changeTo += changeBy;
            GameUIScene.roundNum += 1;
            
            GameUIScene.score += 500;
            GameUIScene.score += GameUIScene.livesNum * 200;
            
            if (GameUIScene.roundNum > 4) {
                GameUIScene.score += 1500;
                
                GameUIScene.roundNum = 1;
                GameUIScene.levelNum += 1;
                changeBy += 1;
                changeTo += 1;
                //playSound(levelcomplete, false);
                
                if (GameUIScene.levelNum > 3) {
                    winScene.onAwake();
                    winScene.setActive();
                }
            }
            
            GameUIScene.cubesVal = (changeTo - 1) % 4;
            
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
