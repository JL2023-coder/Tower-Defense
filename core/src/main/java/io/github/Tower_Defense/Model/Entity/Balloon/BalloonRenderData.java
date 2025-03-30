package io.github.Tower_Defense.Model.Entity.Balloon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BalloonRenderData {
    public final int x, y, width, height;
    public final TextureRegion texture;

    public BalloonRenderData(int x, int y, int width, int height, TextureRegion texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}

