package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;
import static com.tireadev.qbert.Main.scale;

public class GameOverScene extends Scene {
    ShadowEngine se;

    public GameOverScene(ShadowEngine instance) {
        super(instance);
    }

    byte[][] chars = new byte['Z'-44+1][];

    @Override
    public void onAwake() {
        se = instance;

        chars[0] = se.getSubImage(atlas, 0, 0, 8, 8);
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'A'+44),64+8,8,8);
    }

    @Override
    public void onUpdate(float v) {
        se.drawText("GAME  OVER", (16*5)*scale, (16*6)*scale, chars, 44, scale, true);
        se.drawText("Continue A", (16*5)*scale, (16*8)*scale, chars, 44, scale, true);
        se.drawText("Game end B", (16*5)*scale, (16*9)*scale, chars, 44, scale, true);
    }
}