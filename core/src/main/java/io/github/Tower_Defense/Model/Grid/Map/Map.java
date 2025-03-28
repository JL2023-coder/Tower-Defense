package io.github.Tower_Defense.Model.Grid.Map;

import java.util.List;

import io.github.Tower_Defense.Model.Grid.CSVReader;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Grid;

public class Map extends Grid {
    CellPosition startPos;
    CellPosition endPos;
    String mapName;


    public Map(int rows, int cols, String mapName) {
        super(rows, cols);
        this.mapName = mapName;

        initializeMap();

        this.startPos = getTilePosFromTileType(TileType.START);
        this.endPos = getTilePosFromTileType(TileType.END);
    }

    // Returns a 2d array with map values
    public List<List<Integer>> getMap(){
        return CSVReader.readCSV("core/src/main/java/io/github/Tower_Defense/Model/Grid/src/map1.csv");
    }

    // Add values to grid
    public void initializeMap() {
        List<List<Integer>> m = getMap();
        // Update grid to match map size
        int rows = m.size();
        int cols = m.get(0).size();
        this.setRows(rows);
        this.setCols(cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int value = m.get(row).get(col);
                this.setValue(value, new CellPosition(row, col));
            }
        }
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

    public CellPosition getStartPos() {
        return startPos;
    }

    public CellPosition getEndPos() {
        return endPos;
    }
}