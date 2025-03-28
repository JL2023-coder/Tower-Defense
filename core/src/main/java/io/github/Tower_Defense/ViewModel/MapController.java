package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Map.Map;
import io.github.Tower_Defense.Model.Grid.Map.TileType;

public class MapController {
    // Instance Variables
    private Map map;
    private HashMap<CellPosition, CellPosition> path = new HashMap<>();
    private HashSet<CellPosition> visited = new HashSet<>();

    public MapController(){
        this.map = new Map(1, 1, "map1");
        findPath(map.getStartPos());
    }

    public int getStartPosX(){
        return map.getStartPos().col() * map.getCellSize();
    }

    public int getStartPosY(){
        return (map.getRows() - map.getStartPos().row() - 1) * map.getCellSize();
    }

    // Pathfinding
    public void findPath(CellPosition startPos) {
        
        // Clear visited set when starting a new path search
        if (visited.isEmpty()) {
            visited.clear();
            path.clear();
        }
        
        visited.add(startPos);
        ArrayList<CellPosition> neighbours = getNeighbours(startPos);
        
        for (CellPosition pos : neighbours) {
            if (visited.contains(pos) || notValidWayPoint(pos)) {
                continue;
            }
            
            int tileValue = map.getValue(pos);

            if (TileType.END.getValue() == tileValue) {
                path.put(startPos, pos);
                return;
            } 
            
            if (TileType.BLOCKED.getValue() != tileValue) {
                path.put(startPos, pos);
                findPath(pos);
                return;
            }
        }
    }

    private boolean notValidWayPoint(CellPosition pos){
        return pos.row() >= map.getRows() || pos.row() < 0 || 
               pos.col() >= map.getCols() || pos.col() < 0;
    }
    

    // Returns next waypoint
    public CellPosition getNextWayPoint(CellPosition pos){
        try {
            System.out.println(path);
            return path.get(pos);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not get next waypoint");
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