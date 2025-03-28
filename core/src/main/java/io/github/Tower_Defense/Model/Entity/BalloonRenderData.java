package io.github.Tower_Defense.Model.Entity;

import com.badlogic.gdx.graphics.Texture;

public class BalloonRenderData {
    public final int x, y, width, height;
    public final Texture texture;

    public BalloonRenderData(int x, int y, int width, int height, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
    }
}

