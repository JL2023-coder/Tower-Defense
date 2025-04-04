package io.github.Tower_Defense.View;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Tower_Defense.Model.Entity.Balloon.BalloonRenderData;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.ViewModel.ViewModel;

public class View {
    // Instance Variables
    ViewModel viewModel;
    SpriteBatch batch;
    Sprite sprite;

    ShapeRenderer sRenderer;

    // Public Constuctor
    public View(ViewModel viewModel) {
        this.viewModel = viewModel;
        this.batch = new SpriteBatch(); 
    }

    // Renders game, grid, objects etc
    public void renderGame() {
        batch.begin();
        renderGrid();
        renderBalloons();
        batch.end();
    }

    // Renders all balloons, go through all balloons in list and updates position
    private void renderBalloons(){
        ArrayList<BalloonRenderData> balloonRenderDatas = viewModel.getBalloonsRenderData();
        TextureRegion balloonTexture = viewModel.getBalloonTexture();
        sprite = new Sprite(balloonTexture);
        for(BalloonRenderData b : balloonRenderDatas){
            // Config
            sprite.setSize(b.width, b.height);
            sprite.setPosition(b.x - 12, b.y - 38);
            sprite.draw(batch);
        }
    }

    private void renderGrid() {
        int cellSize = viewModel.getCellSize();
    
        for (int row = 0; row < viewModel.getMapRows(); row++) {
            for (int col = 0; col < viewModel.getMapCols(); col++) {
                int x = col * cellSize;
                int y = (viewModel.getMapRows() - row - 1) * cellSize;
                int value = viewModel.getMapValue(new CellPosition(row, col));
    
                batch.draw(viewModel.getTileBatch(value), x, y, cellSize, cellSize);
            }
        }
    }
    

    // Disposes of render
    public void dispose(){
        // Disposes SRenderer
        sRenderer.dispose();
        batch.dispose();;
    }

}
