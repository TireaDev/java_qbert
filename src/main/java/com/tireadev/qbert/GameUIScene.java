package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameUIScene extends Scene {

    static int score = 0, levelNum = 1, roundNum = 1, livesNum = 5, cubesVal = 1;
    byte[] level, round, bertLives, changeTo, arrowL, arrowR;
    byte[][] cubes = new byte[4][];
    byte[][] chars = new byte['Z'-44+1][];

    public void onAwake(){
        level = getSubImage(atlas,240,8*16,32,8);
        round = getSubImage(atlas,240,8+8*16,32,8);
        changeTo = getSubImage(atlas,240,9*16,48,8);
        for (int i = '0'-44; i <= '9'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'0'+44)+5*16,16,8,8);
        for (int i = 'A'-44; i <= 'Z'-44; i++) chars[i] = getSubImage(atlas,128+8*(i-'A'+44),16+8,8,8);
        chars[0] = getSubImage(atlas,280,40,8,8);
        bertLives = getSubImage(atlas,14*16,32,8,10);
        for (int i = 0; i < cubes.length; i++) cubes[i] = getSubImage(atlas,4*16,16*10+i*32,16,16);
        arrowL = getSubImage(atlas,16*16+8,7*16,8,16);
        arrowR = getSubImage(atlas,17*16,7*16,8,16);
    }
    
    public void onUpdate(float deltaTime){
        if (livesNum < 0) livesNum = 0;
        if (roundNum > 4) roundNum = 4;
        if (levelNum > 3) levelNum = 3;

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
        drawImage((40) * scale, (40) * scale, cubes[cubesVal], scale);
        
        drawText(String.format("%05d", score), (24) * scale, (24) * scale, chars, 44, scale, true);
    }
}
