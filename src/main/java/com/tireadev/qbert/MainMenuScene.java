package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class MainMenuScene extends Scene {

    byte[] ultraGames;
    byte[] qbertTitle;
    byte[] bertHimself;
    byte[][] chars = new byte['Z' - 44 + 1][];
    public byte cursorPosition = 0;

    public void onAwake() {
        ultraGames = getSubImage(atlas, 344, 0, 8 * 20, 8 * 4);
        qbertTitle = getSubImage(atlas, 344 - 8, 32, 16 * 10, 16 * 3);
        for (int i = '0' - 44; i <= '9' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - '0' + 44), 64, 8, 8); // řádek 1
        for (int i = 'A' - 44; i <= 'Z' - 44; i++)
            chars[i] = getSubImage(atlas, 128 + 8 * (i - 'A' + 44), 64 + 8, 8, 8); // řádek 2
        chars[0] = getSubImage(atlas, 128 + 80, 64, 8, 8); // ,
        chars[2] = getSubImage(atlas, 128 + 88, 64, 8, 8); // .
        chars[64-44] = getSubImage(atlas, 128 + 96, 64, 16, 16); // @
        bertHimself = getSubImage(atlas, 0, 0, 16, 16);
    }

    @Override
    public void onUpdate(float deltaTime) {
        drawImage((5 * tile + tile/2) * scale, (tile*2 - tile/2) * scale, ultraGames, scale);
        drawImage((3 * tile) * scale, (tile * 3) * scale, qbertTitle, scale);
        drawText("Play Select", (tile * 6 - tile/2) * scale, (tile * 6) * scale, chars, 44, scale, true);
        drawText("Play", (tile * 7) * scale, (tile * 7 + 4) * scale, chars, 44, scale, true);
        drawText("Quit", (tile * 7) * scale, (tile * 8 + 4) * scale, chars, 44, scale, true);
        drawText("TM AND  @  1989", 5 * tile * scale, 10 * tile * scale, chars, 44, scale, true);
        drawText("KONAMI INDUSTRY CO.,LTD.", 2 * tile * scale, (10 * tile * scale) + tile, chars, 44, scale, true);
        drawText("LICENSED BY", 6 * tile * scale, 11 * tile * scale, chars, 44, scale, true);
        drawText("NINTENDO OF AMERICA INC.", 2 * tile * scale, (11 * tile * scale) + tile, chars, 44, scale, true);
        drawText("ULTRA GAMES IS A REGISTERED ", tile * scale, (12 * tile * scale) + tile, chars, 44, scale, true);
        drawText("TRADEMARK OF ULTRA SOFTWARE", tile * scale, 13 * tile * scale, chars, 44, scale, true);
        drawText("CORPORATION.", tile * scale, (13 * tile * scale) + tile, chars, 44, scale, true);

        if ((keyPressed(KEY_DOWN) || keyPressed('S')) && (cursorPosition == 0))
            cursorPosition++;
        if ((keyPressed(KEY_UP) || keyPressed('W')) && (cursorPosition == 1))
            cursorPosition--;

        drawImage((5 * tile + tile/2) * scale, (tile * (7 + cursorPosition)) * scale, bertHimself, scale);

    }
}
