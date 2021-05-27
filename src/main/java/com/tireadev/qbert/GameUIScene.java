package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameUIScene extends Scene {

    static int score, levelNum, roundNum, livesNum, cubesVal;
    byte[] level, round, bertLives, changeTo, arrowL, arrowR;
    byte[][] cubes = new byte[4][];
    byte[][] chars = new byte['Z'-44+1][];

    public void onAwake(){
    
        score = 0;
        levelNum = 1;
        roundNum = 1;
        livesNum = 5;
        cubesVal = 1;
    
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

        drawImage((tile*11 + tile/2) * scale, (tile*2) * scale, level, scale);
        drawImage((tile*13 + tile/2) * scale, (tile*2) * scale, chars['0'+levelNum-44], scale);

        drawImage((tile*11 + tile/2) * scale, (tile*2 + tile/2) * scale, round, scale);
        drawImage((tile*13 + tile/2) * scale, (tile*2 + tile/2) * scale, chars['0'+roundNum-44], scale);

        drawImage((tile) * scale, (tile*2) * scale, changeTo, scale);

        drawImage((tile) * scale, (tile*4) * scale, bertLives, scale);
        drawImage((tile + tile/2) * scale, (tile*4) * scale, chars[0], scale);
        drawImage((tile*2) * scale, (tile*4) * scale, chars['0'+livesNum-44], scale);

        drawImage((tile + tile/2) * scale, (tile*2 + tile/2) * scale, arrowL, scale);
        drawImage((tile*4) * scale, (tile*2 + tile/2) * scale, arrowR, scale);
        drawImage((tile*2 + tile/2) * scale, (tile*2 + tile/2) * scale, cubes[cubesVal], scale);
        
        drawText(String.format("%05d", score), (tile + tile/2) * scale, (tile + tile/2) * scale, chars, 44, scale, true);
    }
}
