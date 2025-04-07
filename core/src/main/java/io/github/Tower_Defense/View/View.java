package io.github.Tower_Defense.View;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Tower_Defense.Model.Entity.Balloon.BalloonRenderData;
import io.github.Tower_Defense.Model.Entity.Tower.TowerRenderData;
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
        this.sRenderer = new ShapeRenderer();
    }

    // Renders game, grid, objects etc
    public void renderGame() {
        batch.begin();
        renderGrid();
        renderBalloons();
        batch.end();
        renderTowers();
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

    // Renders all towers, go through all towers in list
    private void renderTowers(){
        ArrayList<TowerRenderData> towerRenderDatas = viewModel.getTowersRenderData();
        int cellSize = viewModel.getCellSize();

        sRenderer.begin(ShapeRenderer.ShapeType.Filled);
        sRenderer.setColor(Color.BLUE);

        for(TowerRenderData t : towerRenderDatas){
            int x = t.pos.col() * cellSize;
            int y = t.pos.row() * cellSize;

            sRenderer.rect(x, y, cellSize, cellSize);
        }

        sRenderer.end(); // Set rectangle color        
    }
    

    // Disposes of render
    public void dispose(){
        // Disposes SRenderer
        sRenderer.dispose();
        batch.dispose();;
    }

}
