package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class WinScene extends Scene {

    int score = 1;
    byte[][] chars = new byte['Z' - 44+1][];
    byte[] coolGuyL;
    byte[] coolGuyR;
    byte[] ultraGames;
    byte[] qbertTitle;

    public void onAwake() {

        ultraGames = getSubImage(atlas, 344, 0, 8 * 20, 8 * 4);
        qbertTitle = getSubImage(atlas, 344 - 8, 32, 16 * 10, 16 * 3);
        coolGuyL = getSubImage(atlas, 16*2, 16*8, 16, 16);
        coolGuyR = getSubImage(atlas, 16*5, 16*8, 16, 16);
        for (int i = '0' - 44; i <= '9' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - '0' + 44), 64, 8, 8); // řádek 1
        for (int i = 'A' - 44; i <= 'Z' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - 'A' + 44), 64 + 8, 8, 8); // řádek 2
        chars[0] = getSubImage(atlas, 128 + 160, 64, 8, 8); // ,
        chars[2] = getSubImage(atlas, 128 + 168, 64, 8, 8); // .

    }

    @Override
    public void onUpdate(float deltaTime) {

        drawImage((5 * 16 + 8) * scale, (16) * scale, ultraGames, scale);
        drawImage((3 * 16) * scale, (16 * 3 - 8) * scale, qbertTitle, scale);
        drawImage((16*10-8) * scale, (16*6-8) * scale, coolGuyR, scale);
        drawImage((16*5) * scale, (16*6-8) * scale, coolGuyL, scale);
        drawText("YOU WIN", (16 * 6) * scale, (16 * 6) * scale, chars, 44, scale, true);
        drawText("YOUR SCORE IS", (16 * 4 + 8) * scale, (16 * 8) * scale, chars, 44, scale, true);
        drawText(String.format("%05d", score), (16 * 7 - 8) * scale, (16 * 9) * scale, chars, 44, scale, true);

    }
}