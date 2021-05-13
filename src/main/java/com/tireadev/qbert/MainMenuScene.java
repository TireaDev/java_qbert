package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class MainMenuScene extends Scene {

    byte[] ultraGames;
    byte[] qbertTitle;
    byte[] bertHimself;
    byte[][] chars = new byte['Z'-44+1][];
    public byte cursorPosition = 0;


    public void onAwake() {
        ultraGames = getSubImage(atlas,344,0,8*20,8*4);
        qbertTitle = getSubImage(atlas,344-8,32,16*10,16*3);
        for (int i = '0'-44; i <= '9'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'0'+44),64,8,8); // řádek 1
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'A'+44),64+8,8,8); // řádek 2
        chars[0] = getSubImage(atlas, 128+160, 64, 8, 8); // ,
        chars[2] = getSubImage(atlas, 128+168, 64, 8, 8); // .
        bertHimself = getSubImage(atlas,0,0,16,16);
    }


    @Override
    public void onUpdate(float deltaTime) {
        drawImage((5 * 16 + 8) * scale, (32 - 8) * scale, ultraGames, scale);
        drawImage((3 * 16) * scale, (16 * 3) * scale, qbertTitle, scale);
        drawText("Play Select", (16 * 6 - 8) * scale, (16 * 6) * scale, chars, 44, scale, true);
        drawText("Play", (16 * 7) * scale, (16 * 7 + 4) * scale, chars, 44, scale, true);
        drawText("Quit", (16 * 7) * scale, (16 * 8 + 4) * scale, chars, 44, scale, true);
        drawText("TM AND  0  1989", 5 * 16 * scale, 10 * 16 * scale, chars, 44, scale, true);
        drawText("KONAMI INDUSTRY CO.,LTD.", 2 * 16 * scale, (10 * 16 * scale) + 16, chars, 44, scale, true);
        drawText("LICENSED BY", 6 * 16 * scale, 11 * 16 * scale, chars, 44, scale, true);
        drawText("NINTENDO OF AMERICA INC.", 2 * 16 * scale, (11 * 16 * scale) + 16, chars, 44, scale, true);
        drawText("ULTRA GAMES IS A REGISTERED ", 16 * scale, (12 * 16 * scale) + 16, chars, 44, scale, true);
        drawText("TRADEMARK OF ULTRA SOFTWARE", 16 * scale, 13 * 16 * scale, chars, 44, scale, true);
        drawText("CORPORATION.", 16 * scale, (13 * 16 * scale) + 16, chars, 44, scale, true);


        // right 262
        // left 263
        // down 264
        // up 265

        if(keyPressed(264) && (cursorPosition == 0)) cursorPosition++;
        if(keyPressed(265) && (cursorPosition == 1)) cursorPosition--;


        drawImage((5* 16+8) * scale, (16 * (7+cursorPosition )) * scale, bertHimself, scale);






    }
}
