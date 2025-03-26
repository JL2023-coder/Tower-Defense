package io.github.Tower_Defense.Model.Grid.Map;

import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Grid;

public class Map extends Grid {
    CellPosition startPos;
    CellPosition endPos;

    public Map(int rows, int cols) {
        super(rows, cols);
        this.startPos = getTilePosFromTileType(TileType.START);
        this.endPos = getTilePosFromTileType(TileType.END);
    }

    public int getTileValue(TileType tileType) {
        return tileType.getValue();
    }

    // Returns a cellposition that has the same given tile type you give
    private CellPosition getTilePosFromTileType(TileType tileType){
        for(int row = 0; row < getRows(); row++){
            for(int col = 0; col < getCols(); col++){
                CellPosition currentPosition = new CellPosition(row, col);
                if(getValue(currentPosition) == getTileValue(tileType)){
                    return new CellPosition(row, col);
                }
            }
        }
        throw new IndexOutOfBoundsException("Tile type " + tileType + " not found on the map.");
    }
}