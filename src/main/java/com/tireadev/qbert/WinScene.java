package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class WinScene extends Scene {

    byte[][] chars = new byte['Z' - 44+1][];
    byte[] coolGuyL;
    byte[] coolGuyR;

    public void onAwake() {
        coolGuyL = getSubImage(atlas, 16*2, 16*8, 16, 16);
        coolGuyR = getSubImage(atlas, 16*5, 16*8, 16, 16);
        
        for (int i = '0' - 44; i <= '9' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - '0' + 44), 64, 8, 8); // řádek 1
        for (int i = 'A' - 44; i <= 'Z' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - 'A' + 44), 64 + 8, 8, 8); // řádek 2
        chars[0] = getSubImage(atlas, 128 + 80, 64, 8, 8); // ,
        chars[2] = getSubImage(atlas, 128 + 88, 64, 8, 8); // .
    }

    @Override
    public void onUpdate(float deltaTime) {
        drawImage((tile*10-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyR, scale);
        drawImage((tile*5-tile/2) * scale, (tile*6-tile/2) * scale, coolGuyL, scale);
        drawText("YOU  WIN", (tile * 6 - tile/2) * scale, (tile * 6) * scale, chars, 44, scale, true);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, chars, 44, scale, true);
        drawText("Continue A", (tile * 5) * scale, (tile * 8) * scale, chars, 44, scale, true);
        drawText("Game end B", (tile * 5) * scale, (tile * 9) * scale, chars, 44, scale, true);
    }
}