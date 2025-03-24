package io.github.Tower_Defense.View;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.ViewModel.ViewModel;

public class View {
    // Instance Variables
    int cellSize = 32;
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
        renderGrid();
        renderBalloons();
    }

    // Renders all balloons, go through all balloons in list and updates position
    private void renderBalloons(){
        ArrayList<Balloon> balloons = viewModel.getBalloons();
        sRenderer.begin(ShapeType.Filled);
        sRenderer.setColor(Color.BLUE);
        for(Balloon b:balloons){
            sRenderer.rect(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
        }
        sRenderer.end();
    }

    // Iterates through grid, and renders
    private void renderGrid(){
        int rows = viewModel.getGridRows();
        int cols = viewModel.getGridCols();

        // Adding tiles
        batch = new SpriteBatch();
        batch.begin();
        for (int row = 0; row < viewModel.getGridRows(); row++) {
            for (int col = 0; col < viewModel.getGridCols(); col++) {

                int x = col * cellSize;
                int y = (viewModel.getGridRows() - row - 1) * cellSize;

                // System.out.println("pos=" + pos);
                // System.out.println("-----");
                // System.out.println("value=" + gameBoard.get(pos));
                // System.out.println("-----");
                
		        batch.draw(viewModel.getTileBatch(viewModel.getGridValue(row, col)), x, y, cellSize, cellSize);
            }
        }
        batch.end();

        // Iterates through cell, draw line
        sRenderer.begin(ShapeType.Line);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
			    sRenderer.setColor(Color.RED);
                sRenderer.rect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        sRenderer.end();
    }

    // Disposes of render
    public void dispose(){
        // Disposes SRenderer
        sRenderer.dispose();
    }

}
