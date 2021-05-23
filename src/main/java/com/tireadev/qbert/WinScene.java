package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class WinScene extends Scene {

    byte[] coolGuyL;
    byte[] coolGuyR;

    public void onAwake() {
        coolGuyL = getSubImage(atlas, 16*2, 16*8, 16, 16);
        coolGuyR = getSubImage(atlas, 16*5, 16*8, 16, 16);
    }

    @Override
    public void onUpdate(float deltaTime) {
        drawImage((tile*10-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyR, scale);
        drawImage((tile*5-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyL, scale);
        drawText("YOU  WIN", (tile * 6 - tile/2) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, white_font, white_font_offset, scale, true);
        drawText("Continue A", (tile * 5) * scale, (tile * 8) * scale, white_font, white_font_offset, scale, true);
        drawText("Game end B", (tile * 5) * scale, (tile * 9) * scale, white_font, white_font_offset, scale, true);
    }
}