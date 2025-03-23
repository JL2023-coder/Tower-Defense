package io.github.Tower_Defense.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import io.github.Tower_Defense.Model.Grid.Grid;

public class View {
    // Instance Variables
    Grid grid;
    int cellSize = 32;

    ShapeRenderer sRenderer;

    // Public Constuctor
    public View(Grid grid){
        this.grid = grid;
        sRenderer = new ShapeRenderer();
    }

    // Renders game, grid, objects etc
    public void renderGame(){
        renderGrid();

    }

    private void renderGrid(){
        int rows = grid.getRows();
        int cols = grid.getCols();

        // Iterates thorugh cell
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
