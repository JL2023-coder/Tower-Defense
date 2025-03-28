package io.github.Tower_Defense.Model.Grid;

public class Grid implements IGrid {
    // Instance Variables
    private int[][] grid;
    private final int cellSize = 32;

    // Public Constructor
    public Grid(int rows, int cols){
        this.grid = new int[rows][cols];

        // Initalizes Grid with 0
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[0].length; col++){
                setValue(0, new CellPosition(row, col));
            }
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    @Override
    public int getRows() {
        return this.grid.length;
    }

    @Override
    public void setRows(int rows) {
        resizeGrid(rows, getCols());
    }

    @Override
    public int getCols() {
        return this.grid[0].length;
    }

    @Override
    public void setCols(int cols) {
        resizeGrid(getRows(), cols);
    }

    @Override
    public void setValue(int value, CellPosition pos) {
        int row = pos.row();
        int col = pos.col();

        if (positionIsOnGrid(pos) == false){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        else{
            grid[row][col] = value;
        }

    }

    @Override
    public int getValue(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();
        // if position does not exist
        // @return Out of bounds
        if (positionIsOnGrid(pos) == false){
            throw new IndexOutOfBoundsException("Out of bounds");
        }
        else{
            return grid[row][col];
        }

    }

    @Override
    public void resizeGrid(int newRows, int newCols) {
        this.grid = new int[newRows][newCols];
    }

    @Override
    // @return True if position exist on grid
    // false otherwise
    public boolean positionIsOnGrid(CellPosition pos) {
        int row = pos.row();
        int col = pos.col();

        return !(row < 0 || row >= getRows() || col <0 || col >=getCols());
    }

}
