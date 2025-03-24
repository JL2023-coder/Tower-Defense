package io.github.Tower_Defense.Model.Grid;

public interface IGrid {
    int getRows();
    int getCols();
    void setRows(int rows);
    void setCols(int cols);
    void setValue(int value, int row, int col);
    int getValue(int row, int col);
    void resizeGrid(int newRows, int newCols);
}
