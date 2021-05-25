package com.tireadev.qbert;

import com.tireadev.shadowengine.math.Vec2i;

public abstract class Entity {
    
    Vec2i pos, dir;
    
    byte[] sprite;
    byte[][] sprites = new byte[8][];
    
    public EntityType type;

    public Entity(Vec2i pos, byte[][] sprites, EntityType type) {
        this.pos = pos;
        this.dir = new Vec2i();
        this.sprites = sprites;
        this.sprite = sprites[0];
        this.type = type;
    }
    
    void move() {
        boolean isEven = (pos.y * MapScene.mapWidth + 1) % 2 == 0;
        
        int x = pos.x, y = pos.y + dir.y;
        
        if (isEven && dir.x == 1 || !isEven && dir.x == -1) {
            x = pos.x + dir.x;
        }
        
        if (dir.x == 1 && dir.y == -1) {
            sprite = sprites[0];
        } else if (dir.x == -1 && dir.y == -1) {
            sprite = sprites[2];
        } else if (dir.x == 1 && dir.y == 1) {
            sprite = sprites[4];
        } else if (dir.x == -1 && dir.y == 1) {
            sprite = sprites[6];
        }
        
        try {
            if (MapScene.map[y * MapScene.mapWidth + x] > 0) {
                pos.x = x;
                pos.y = y;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            pos.x = x;
            pos.y = y;
        }
    }
    
    public abstract void update(float deltaTime);
    
    public abstract void spawn();
    
}

enum EntityType {
    PLAYER,
    ENEMY
}