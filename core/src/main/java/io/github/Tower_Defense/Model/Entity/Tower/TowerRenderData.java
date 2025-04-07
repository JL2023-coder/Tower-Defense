package io.github.Tower_Defense.Model.Entity.Tower;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.Tower_Defense.Model.Grid.CellPosition;

public class TowerRenderData {
    public final CellPosition pos;
    public final int width, height;

    public TowerRenderData(CellPosition pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
    }
}
