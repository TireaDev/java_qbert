package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameOverScene extends Scene {

    byte[] qbert;
    int cursorPosition;

    @Override
    public void onAwake() {
        qbert = getSubImage(atlas, 14*16, 32, 8, 10);
    }

    @Override
    public void onUpdate(float v) {
        drawText("GAME  OVER", (tile * 5) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, white_font, white_font_offset, scale, true);
        drawText("Continue", (tile * 6) * scale, (tile * 8) * scale, white_font, white_font_offset, scale, true);
        drawText("Game end", (tile * 6) * scale, (tile * 9) * scale, white_font, white_font_offset, scale, true);

        if ((keyPressed(KEY_DOWN) || keyPressed('S')) && (cursorPosition == 0))
            cursorPosition++;
        if ((keyPressed(KEY_UP) || keyPressed('W')) && (cursorPosition == 1))
            cursorPosition--;

        drawImage(tile  * 5 * scale, tile * (8 + cursorPosition) * scale, qbert, scale);
    }
}