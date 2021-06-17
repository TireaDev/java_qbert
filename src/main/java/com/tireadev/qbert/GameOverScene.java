package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class GameOverScene extends Scene {

    byte[] qbert;
    int cursorPosition;

    float timer;
    
    byte[] gameover;
    byte[] option;

    @Override
    public void onAwake() {
        qbert = getSubImage(atlas, 14*16, 32, 8, 10);
        timer = 0;
        
        stopAllSounds();
        
        gameover = loadSound(res_path + "sound_effects/game_over.wav");
        option = loadSound(res_path + "sound_effects/jump.wav");
        
        playSound(gameover, true);
    }

    @Override
    public void onUpdate(float deltaTime) {
        timer += deltaTime;
        
        drawText("GAME  OVER", (tile * 5) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true, (timer)*1.5f);
        drawText(String.format("%05d", GameUIScene.score), (tile * 7 - tile/2) * scale, (tile * 7) * scale, white_font, white_font_offset, scale, true, (timer-.2f)*1.5f);
        drawText("Continue", (tile * 6) * scale, (tile * 8) * scale, white_font, white_font_offset, scale, true, (timer-.4f)*1.5f);
        drawText("Game end", (tile * 6) * scale, (tile * 9) * scale, white_font, white_font_offset, scale, true, (timer-.6f)*1.5f);

        if ((keyPressed(KEY_DOWN) || keyPressed('S')) && (cursorPosition == 0)){
            cursorPosition++;
            playSound(option, false);
        }
        if ((keyPressed(KEY_UP) || keyPressed('W')) && (cursorPosition == 1)){
            cursorPosition--;
            playSound(option, false);
        }

        drawImage(tile * 5 * scale, tile * (8 + cursorPosition) * scale, qbert, scale, (timer-.8f)*1.5f);
    }
}