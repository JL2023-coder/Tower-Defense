package io.github.Tower_Defense.Model.Grid;

public class Grid implements IGrid {
    // Instance Variables
    private int[][] grid;

    // Public Constructor
    public Grid(int rows, int cols){
        this.grid = new int[rows][cols];

        // Initalizes Grid with 0
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                setValue(0, row, col);
            }
        }
    }

    @Override
    public int getRows() {
        return this.grid.length;
    }

    @Override
    public int getCols() {
        return this.grid[0].length;
    }

    @Override
    public void setValue(int value, int row, int col) {
        this.grid[row][col] = value;
    }

    @Override
    public int getValue(int row, int col) {
        return this.grid[row][col];
    }



}
