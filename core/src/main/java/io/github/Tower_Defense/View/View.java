package io.github.Tower_Defense.View;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonRenderData;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.ViewModel.ViewModel;

public class View {
    // Instance Variables
    ViewModel viewModel;
    private SpriteBatch batch;

    ShapeRenderer sRenderer;

    // Public Constuctor
    public View(ViewModel viewModel){
        this.viewModel = viewModel;

        sRenderer = new ShapeRenderer();
    }

    // Renders game, grid, objects etc
    public void renderGame(){
        batch = new SpriteBatch();
        batch.begin();
        renderGrid();
        renderBalloons();
        batch.end();
    }

    // Renders all balloons, go through all balloons in list and updates position
    private void renderBalloons(){
        Sprite sprite = new Sprite(viewModel.getBalloonTexture());
        ArrayList<BalloonRenderData> balloonRenderDatas = viewModel.getBalloonsRenderData();
        for(BalloonRenderData b : balloonRenderDatas){
            // Config
            int posX = b.x;
            int posY = b.y;
            int width = b.width;
            int height = b.height;
            sprite.setSize(width, height);
            sprite.setOriginCenter();  // Sets origin to (width/2, height/2)
            sprite.setPosition(posX - viewModel.getCellSize()/2, posY - height / 2); // Centers it
            sprite.draw(batch);
        }
    }

    // Iterates through grid, and renders
    private void renderGrid(){
        // Adding tiles
        for (int row = 0; row < viewModel.getMapRows(); row++) {
            for (int col = 0; col < viewModel.getMapCols(); col++) {
                int x = col * viewModel.getCellSize();
                int y = (viewModel.getMapRows() - row - 1) * viewModel.getCellSize();

                int value = viewModel.getMapValue(new CellPosition(row, col));
                // System.out.println("pos=" + pos);
                // System.out.println("-----");
                // System.out.println("value=" + gameBoard.get(pos));
                // System.out.println("-----");
                
		        batch.draw(viewModel.getTileBatch(value), x, y, viewModel.getCellSize(), viewModel.getCellSize());
            }
        }

        /* // Iterates through cell, draw line
        sRenderer.begin(ShapeType.Line);

        int rows = viewModel.getMapRows();
        int cols = viewModel.getMapCols();
        
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
			    sRenderer.setColor(Color.RED);
                sRenderer.rect(col * viewModel.getCellSize(), row * viewModel.getCellSize(), viewModel.getCellSize(), viewModel.getCellSize());
            }
        }
        sRenderer.end(); */
    }

    // Disposes of render
    public void dispose(){
        // Disposes SRenderer
        sRenderer.dispose();
        batch.dispose();;
    }

}
