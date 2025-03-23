package io.github.Tower_Defense.Model.Grid;

public interface IGrid {
    public int getRows();
    public int getCols();

    public void setValue(int value, int row, int col);
    public int getValue(int row, int col);
}
