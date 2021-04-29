package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;
import com.tireadev.shadowengine.ShadowEngine;

import static com.tireadev.qbert.Main.atlas;
import static com.tireadev.qbert.Main.scale;

public class MainMenuScene extends Scene {

    ShadowEngine se;


    public MainMenuScene(ShadowEngine instance) {
        super(instance);
    }

    byte[] ultraGames;
    byte[] qbertTitle;
    byte[] bertHimself;
    byte[][] chars = new byte['Z'-44+1][];
    //byte[][] nums = new byte[10][];


    public void onAwake() {
        se = instance;
        ultraGames = se.getSubImage(atlas,344,0,8*20,8*4);
        qbertTitle = se.getSubImage(atlas,344-8,32,16*10,16*3);
        for (int i = '0'-44; i <= '9'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'0'+44),64,8,8); // řádek 1
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = se.getSubImage(atlas,128+8*(i-'A'+44),64+8,8,8); // řádek 2
        chars[0] = se.getSubImage(atlas, 128+160, 64, 8, 8); // ,
        chars[2] = se.getSubImage(atlas, 128+168, 64, 8, 8); // .
        bertHimself = se.getSubImage(atlas,0,0,16,16);
    }


    @Override
    public void onUpdate(float deltaTime) {
        se.drawImage((5 * 16 + 8) * scale, (32 - 8) * scale, ultraGames, scale);
        se.drawImage((3 * 16) * scale, (16 * 3) * scale, qbertTitle, scale);
        se.drawText("Play Select", (16 * 6 - 8) * scale, (16 * 6) * scale, chars, 44, scale, true);
        se.drawText("Play", (16 * 7) * scale, (16 * 7) * scale, chars, 44, scale, true);
        se.drawText("Quit", (16 * 7) * scale, (16 * 8) * scale, chars, 44, scale, true);
        se.drawImage((5 * 16) * scale, (16 * 7) * scale, bertHimself, scale);
        se.drawText("TM AND  0  1989", 5 * 16 * scale, 10 * 16 * scale, chars, 44, scale, true);
        se.drawText("KONAMI INDUSTRY CO.,LTD.", 2 * 16 * scale, (10 * 16 * scale) + 16, chars, 44, scale, true);
        se.drawText("LICENSED BY", 6 * 16 * scale, 11 * 16 * scale, chars, 44, scale, true);
        se.drawText("NINTENDO OF AMERICA INC.", 2 * 16 * scale, (11 * 16 * scale) + 16, chars, 44, scale, true);
        se.drawText("ULTRA GAMES IS A REGISTERED ", 16 * scale, (12 * 16 * scale) + 16, chars, 44, scale, true);
        se.drawText("TRADEMARK OF ULTRA SOFTWARE", 16 * scale, 13 * 16 * scale, chars, 44, scale, true);
        se.drawText("CORPORATION.", 16 * scale, (13 * 16 * scale) + 16, chars, 44, scale, true);

    }


}
