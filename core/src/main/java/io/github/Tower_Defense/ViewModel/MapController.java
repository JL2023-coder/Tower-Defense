package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.github.Tower_Defense.Model.Grid.CSVReader;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Map.Map;
import io.github.Tower_Defense.Model.Grid.Map.TileType;

public class MapController {
    // Instance Variables
    private Map map;
    private ArrayList<CellPosition> path;
    private CellPosition lastPos;

    public MapController(Map map){
        this.map = map;
        
        // Add values to grid
        // Should be removed and added to controller later
        initializeMap();
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getStartPosX(){
        return map.getStartPos().col() * map.getCellSize();
    }

    public int getStartPosY(){
        return map.getStartPos().row() * map.getCellSize();
    }

    // Returns a 2d array with map values
    public List<List<Integer>> getMap(){
        return CSVReader.readCSV("core/src/main/java/io/github/Tower_Defense/Model/Grid/src/map1.csv");
    }

    // Add values to grid
    public void initializeMap() {
        List<List<Integer>> map = getMap();
        // Update grid to match map size
        int rows = map.size();
        int cols = map.get(0).size();
        this.map.setRows(rows);
        this.map.setCols(cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int value = map.get(row).get(col);
                this.map.setValue(value, new CellPosition(row, col));
            }
        }
    }

    // Pathfinding
    public void findPath(CellPosition startPos){
        ArrayList<CellPosition> neighbours = getNeighbours(startPos);
        for(CellPosition pos : neighbours){
            map.getValue(startPos);
            if(pos == lastPos) {
                continue;
            }
            else if(TileType.END.getValue() == map.getValue(pos)) {
                path.add(pos);
            }
            else if(TileType.BLOCKED.getValue() != map.getValue(pos)) {
                path.add(pos);
                lastPos = pos;
                findPath(pos);
            }
        }
    }

    private ArrayList<CellPosition> getNeighbours(CellPosition pos) {
        ArrayList<CellPosition> neighbours = new ArrayList<>();
        // Directions
        int[][] directions = {
            {0, -1},  // Left
            {0, 1},   // Right
            {-1, 0},  // Above
            {1, 0}    // Below
        };
    
        // Iterate over each direction
        for (int[] d : directions) {
            CellPosition neighbour = new CellPosition(pos.row() + d[0], pos.col() + d[1]);
            if (map.positionIsOnGrid(neighbour)) {
                neighbours.add(neighbour);
            }
        }
    
        return neighbours;
    }
}
