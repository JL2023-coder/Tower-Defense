package io.github.Tower_Defense.View;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.ViewModel.ViewModel;

public class View {
    // Instance Variables
    int cellSize = 32;
    ViewModel viewModel;

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

    private void renderBalloons(){
        ArrayList<Balloon> balloons = viewModel.getBalloons();
        sRenderer.begin(ShapeType.Filled);
        sRenderer.setColor(Color.BLUE);
        for(Balloon b:balloons){
            sRenderer.rect(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
        }
        sRenderer.end();
    }

    private void renderGrid(){
        int rows = viewModel.getGridRows();
        int cols = viewModel.getGridCols();

        // Iterate thorugh cells, fills cells
        sRenderer.begin(ShapeType.Filled);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
			    if(viewModel.getGridValue(row, col)==1){
                    sRenderer.setColor(Color.BLACK);
                }
                else{
                    sRenderer.setColor(Color.BLACK);
                }
                sRenderer.rect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        sRenderer.end();

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

    public void dispose(){
        sRenderer.dispose();
    }

}
