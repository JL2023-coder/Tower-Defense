package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Map.Map;

public class MapController {
    // Instance Variables
    private Map map;
    private ArrayList<CellPosition> path;

    public MapController(Map map){
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    // Pathfinding
    public void findPath(CellPosition startPos){
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
