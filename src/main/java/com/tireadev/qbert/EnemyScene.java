package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;

public class EnemyScene extends Scene {

    ShadowEngine se;

    final int enemyStartX = 7 * 32 + 16;
    final int enemyStartY = 3 * 32 - 24;
    int enemyX = enemyStartX;
    int enemyY = enemyStartY;
    int xDirection;
    byte[][] enemies = new byte[4][];
    final int speed = 60;
    int timer = 0;
    final int rows = 7;
    int verticalPosition = 1;
    int waitTime = (int)(Math.random() * 4) + 2;
    int wait = 0;

    public EnemyScene(ShadowEngine instance) {
        super(instance);
        this.se = this.instance;
    }

    public void resetEnemyXY(){
        this.enemyX = this.enemyStartX;
        this.enemyY = this.enemyStartY;
    }

    public void checkCollisions(){

    }

    @Override
    public void onAwake() {
        enemies[0] = se.getSubImage(atlas, 5*16, 16, 16, 16);
    }

    @Override
    public void onUpdate(float v) {
        if(timer == speed){
            if(wait == waitTime){
                if(verticalPosition != rows){
                    this.xDirection = (int)(Math.random()*2);
                    switch(xDirection){
                        case 0: this.enemyX = this.enemyX + Main.tile;
                            break;
                        case 1: this.enemyX = this.enemyX - Main.tile;
                    }

                    this.enemyY = this.enemyY + Main.tile + (Main.tile / Main.scale);
                    verticalPosition++;

                }else{
                    resetEnemyXY();
                    verticalPosition = 1;
                    wait = 0;
                    waitTime = (int)(Math.random() * 2) + 2;

                }
            }else{
                wait++;
            }
            timer = 0;

        }else{
            timer++;
        }

        if(wait == waitTime){
            se.drawImage(this.enemyX, this.enemyY, enemies[0], Main.scale);
        }
    }
}
