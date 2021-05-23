package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameOverScene extends Scene {
    @Override
    public void onUpdate(float v) {
        drawText("GAME  OVER", (tile * 5) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, white_font, white_font_offset, scale, true);
        drawText("Continue A", (tile * 5) * scale, (tile * 8) * scale, white_font, white_font_offset, scale, true);
        drawText("Game end B", (tile * 5) * scale, (tile * 9) * scale, white_font, white_font_offset, scale, true);
    }
}