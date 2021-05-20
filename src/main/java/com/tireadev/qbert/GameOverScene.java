package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameOverScene extends Scene {

    byte[][] chars = new byte['Z' - 44 + 1][];

    @Override
    public void onAwake() {
        for (int i = '0' - 44; i <= '9' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - '0' + 44), 64, 8, 8); // řádek 1
        for (int i = 'A' - 44; i <= 'Z' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - 'A' + 44), 64 + 8, 8, 8); // řádek 2
        chars[0] = getSubImage(atlas, 128 + 80, 64, 8, 8); // ,
        chars[2] = getSubImage(atlas, 128 + 88, 64, 8, 8); // .
    }

    @Override
    public void onUpdate(float v) {
        drawText("GAME  OVER", (16 * 5) * scale, (16 * 6) * scale, chars, 44, scale, true);
        drawText(String.format("%05d", GameUIScene.score), (16 * 7 - 8) * scale, (16 * 7) * scale, chars, 44, scale, true);
        drawText("Continue A", (16 * 5) * scale, (16 * 8) * scale, chars, 44, scale, true);
        drawText("Game end B", (16 * 5) * scale, (16 * 9) * scale, chars, 44, scale, true);
    }
}