package io.github.Tower_Defense.Model.Grid;

public interface IGrid {
    int getRows();
    int getCols();
    void setRows(int rows);
    void setCols(int cols);
    void setValue(int value, CellPosition pos);
    int getValue(CellPosition pos);
    void resizeGrid(int newRows, int newCols);
    boolean positionIsOnGrid(CellPosition pos);
}
