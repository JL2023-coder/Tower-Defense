package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;

import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Map.Map;
import io.github.Tower_Defense.Model.Grid.Map.TileType;

public class MapController {
    // Instance Variables
    private Map map;
    private ArrayList<CellPosition> path;
    private CellPosition lastPos;

    public MapController(){
        this.map = new Map(10, 10, "map1");
    }

    public int getStartPosX(){
        return map.getStartPos().col() * map.getCellSize();
    }

    public int getStartPosY(){
        return ((map.getRows() - map.getStartPos().row() - 1) * map.getCellSize());
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

    public int getMapValue(CellPosition pos){
        return map.getValue(pos);
    }

    public int getMapRows(){
        return map.getRows();
    }

    public int getMapCols(){
        return map.getCols();
    }

    public int getCellSize(){
        return map.getCellSize();
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