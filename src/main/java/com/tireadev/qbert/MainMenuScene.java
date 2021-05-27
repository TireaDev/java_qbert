package com.tireadev.qbert;

import com.tireadev.shadowengine.Scene;

import static com.tireadev.qbert.Main.*;

public class MainMenuScene extends Scene {

    byte[] ultraGames;
    byte[] qbertTitle;
    byte[] bertHimself;
    public byte cursorPosition = 0;
    
    float timer;

    public void onAwake() {
        byte[] startsound;
        startsound = loadSound("src/main/resources/sound_effects/level_start.wav");
        playSound(startsound, false);
        ultraGames = getSubImage(atlas, 344, 0, 8 * 20, 8 * 4);
        qbertTitle = getSubImage(atlas, 344 - 8, 32, 16 * 10, 16 * 3);
        bertHimself = getSubImage(atlas, 0, 0, 16, 16);
        
        timer = 0;
    }

    @Override
    public void onUpdate(float deltaTime) {
        byte[] option;
        option = loadSound("src/main/resources/sound_effects/jump.wav");
        timer += deltaTime;
    
        drawImage((5 * tile + tile/2) * scale, (tile*2 - tile/2) * scale, ultraGames, scale, timer-1);
        drawImage((3 * tile) * scale, (tile * 3) * scale, qbertTitle, scale, timer-1.2f);
        drawText("Play Select", (tile * 6 - tile/2) * scale, (tile * 6) * scale, white_font, white_font_offset, scale, true, (timer-2)*1.4f);
        drawText("Play", (tile * 7) * scale, (tile * 7 + tile/4) * scale, white_font, white_font_offset, scale, true, (timer-2.1f)*1.2f);
        drawText("Quit", (tile * 7) * scale, (tile * 8 + tile/4) * scale, white_font, white_font_offset, scale, true, (timer-2.2f)*1.2f);
        drawText("TM AND  @  1989", 5 * tile * scale, 10 * tile * scale, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("KONAMI INDUSTRY CO.,LTD.", 2 * tile * scale, (10 * tile * scale) + tile, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("LICENSED BY", 6 * tile * scale, 11 * tile * scale, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("NINTENDO OF AMERICA INC.", 2 * tile * scale, (11 * tile * scale) + tile, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("ULTRA GAMES IS A REGISTERED ", tile * scale, (12 * tile * scale) + tile, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("TRADEMARK OF ULTRA SOFTWARE", tile * scale, 13 * tile * scale, white_font, white_font_offset, scale, true, timer-1.5f);
        drawText("CORPORATION.", tile * scale, (13 * tile * scale) + tile, white_font, white_font_offset, scale, true, timer-1.5f);

        if ((keyPressed(KEY_DOWN) || keyPressed('S')) && (cursorPosition == 0)){
            cursorPosition++;
        playSound(option, false);}
        if ((keyPressed(KEY_UP) || keyPressed('W')) && (cursorPosition == 1)){
            cursorPosition--;
        playSound(option, false);}

        drawImage((5 * tile + tile/2) * scale, (tile * (7 + cursorPosition)) * scale, bertHimself, scale, (timer-2.3f)*1.2f);
    }
}
