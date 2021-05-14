package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class EnemyScene extends Scene {

    final int enemyStartX = 3;
    final int enemyStartY = 0;
    int enemyX = enemyStartX;
    int enemyY = enemyStartY;
    final int startEo = 0; // 0 = even, 1 = odd
    int eo = startEo;
    int enemyDirection; // 0 = left, 1 = right
    int delay = 0;

    byte[][] enemy = new byte[4][];

    static final byte mapWidth = 7;

    public static final byte[] enemies = new byte[mapWidth * mapWidth];

    public void resetEnemyXY() {
        enemyX = enemyStartX;
        enemyY = enemyStartY;
    }

    public void checkCollisions() {

    }

    @Override
    public void onAwake() {
        enemy[1] = getSubImage(atlas, 5 * 16, 1 * 16, 16, 16);
        enemy[0] = getSubImage(atlas, 6 * 16, 1 * 16, 16, 16);

        enemies[3] = 1;
    }

    @Override
    public void onUpdate(float v) {

        if (delay == 60) {
            if (enemyY == 6) {
                enemies[enemyY * 7 + enemyX] = 0;
                resetEnemyXY();
                enemies[enemyY * 7 + enemyX] = 1;
            } else {
                enemyDirection = (int) (Math.random() * 2);
                enemies[enemyY * 7 + enemyX] = 0;
                if (enemyDirection == 1) { // 1 = right, 0 = left
                    enemyY++;
                    if (eo == 1) { // 0 = even, 1 = odd
                        enemyX++;
                        eo = 0;
                    } else {
                        eo = 1;
                    }
                } else {
                    enemyY++;
                    if (eo == 0) {
                        enemyX--;
                        eo = 1;
                    } else {
                        eo = 0;
                    }
                }

                enemies[enemyY * 7 + enemyX] = 1;
            }
            delay = 0;
        } else {
            delay++;
        }

        for (int y = 0; y < mapWidth; y++) {
            for (int x = 0; x < mapWidth; x++) {
                int val = enemies[y * mapWidth + x];
                if (val > 0) {

                    int ox = 0, oy = tile * 3 / 4 * scale;
                    if (y % 2 == 1)
                        ox = tile / 2 * scale;

                    ox += tile / 2 * scale;

                    int tx = x * tile * scale + ox;
                    int ty = y * oy + (tile * scale * 5 / 4);

                    if (val >= enemy.length)
                        val = 0;
                    drawImage(tx + 16, ty - 8, enemy[val], scale);
                }
            }
        }
    }
}
