package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameUIScene extends Scene {

    byte[] level;
    byte[] round;
    byte[] changeTo;
    byte[][] cubes = new byte[3][];
    byte[][] chars = new byte['Z'-44+1][];
    byte[] bertLives;
    int score = 0;
    int val = 0;
    int livesNum = 5;
    int roundNum = 1;
    int levelNum = 1;
    byte[] arrowL;
    byte[] arrowR;

    public void onAwake(){

        level = getSubImage(atlas,240,8*16,32,8);
        round = getSubImage(atlas,240,8+8*16,32,8);
        changeTo = getSubImage(atlas,240,9*16,48,8);
        for (int i = '0'-44; i <= '9'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'0'+44)+5*16,16,8,8);
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'A'+44),16+8,8,8);
        chars[0] = getSubImage(atlas,280,40,8,8);
        bertLives = getSubImage(atlas,14*16,32,8,10);
        for (int i = 0; i < 3; i++) cubes[i] = getSubImage(atlas,4*16,16*10+i*32,16,16);
        arrowL = getSubImage(atlas,16*16+8,7*16,8,16);
        arrowR = getSubImage(atlas,17*16,7*16,8,16);


    }
    public void onUpdate(float deltaTime){

        drawImage((11 * 16 + 8) * scale, (32) * scale, level, scale);
        drawImage((13*16+8) * scale, (32) * scale, chars['0'+levelNum-44], scale);

        drawImage((11 * 16 + 8) * scale, (40) * scale, round, scale);
        drawImage((13*16+8) * scale, (40) * scale, chars['0'+roundNum-44], scale);

        drawImage((16) * scale, (32) * scale, changeTo, scale);

        drawImage((16) * scale, (64) * scale, bertLives, scale);
        drawImage((24) * scale, (64) * scale, chars[0], scale);
        drawImage((32) * scale, (64) * scale, chars['0'+livesNum-44], scale);

        drawImage((24) * scale, (40) * scale, arrowL, scale);
        drawImage((64) * scale, (40) * scale, arrowR, scale);




        if(keyPressed('H')) score++;
        if(keyPressed('C')) val++;
        if(val >= 3) val = 0;
        if(keyPressed('O')) livesNum--;
        if(keyPressed('P')) roundNum++;
        if(keyPressed('I')) levelNum++;


        drawImage((40) * scale, (40) * scale, cubes[val], scale);
        drawText("score", (16) * scale, (16) * scale, chars, 44, scale, true);
        drawText(String.format("%05d", score), (24) * scale, (24) * scale, chars, 44, scale, true);



    }



}
