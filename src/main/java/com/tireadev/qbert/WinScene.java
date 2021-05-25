package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class WinScene extends Scene {

    byte[] coolGuyL;
    byte[] coolGuyR;
    byte[] qbert;

    int cursorPosition;
    float timer;


    public void onAwake() {
        coolGuyL = getSubImage(atlas, 16*2, 16*8, 16, 16);
        coolGuyR = getSubImage(atlas, 16*5, 16*8, 16, 16);
        qbert = getSubImage(atlas, 14*16, 32, 8, 10);
        timer = 0;
    }

    @Override
    public void onUpdate(float deltaTime) {
        timer += deltaTime;
        drawImage((tile*10-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyR, scale, timer);
        drawImage((tile*5-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyL, scale, timer);
        drawText("YOU  WIN", (tile * 6 - tile/2) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true, timer);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, white_font, white_font_offset, scale, true, timer);
        drawText("Continue", (tile * 6) * scale, (tile * 8) * scale, white_font, white_font_offset, scale, true, timer-2);
        drawText("Game end", (tile * 6) * scale, (tile * 9) * scale, white_font, white_font_offset, scale, true, timer-2);

        if ((keyPressed(KEY_DOWN) || keyPressed('S')) && (cursorPosition == 0))
            cursorPosition++;
        if ((keyPressed(KEY_UP) || keyPressed('W')) && (cursorPosition == 1))
            cursorPosition--;

        drawImage(tile  * 5 * scale, tile * (8 + cursorPosition) * scale, qbert, scale, timer-2);
    }
}