package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.CSVReader;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.Model.Grid.Map.Map;
import io.github.Tower_Defense.Model.Grid.Map.TileSet;

public class ViewModel {
    // Instance Variables
    BalloonFactory factory;
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    Grid grid;
    // Time between each spawn
    static float SPAWN_INTERVAL = 3;
    // Time since last spawn
    float timeSinceLastSpawn = 0;
    // Map map
    Map map;
    // MapController
    MapController mapController;

    // Public constructor
    public ViewModel(MapController mapController){
        this.factory = new BalloonFactory();
        this.mapController = mapController;
    }

    // Updates game, moves balloons and spawn in
    public void update(float delta){
        spawnBalloon(delta);
        moveBalloons(delta);
    }

    // Move all balloons in list
    private void moveBalloons(float delta){
        for(Balloon b:balloons){
            b.move(delta, 'R');
        }
    }

    // Spawns balloon if timeSinceLastSpawn is larger than SpawnInterval
    private void spawnBalloon(float delta){
        timeSinceLastSpawn += delta;
        if(timeSinceLastSpawn >= SPAWN_INTERVAL){
            balloons.add(factory.getNext(mapController.getStartPosX(), mapController.getStartPosY()));
            timeSinceLastSpawn = 0;
        }
    }
    // Returns list of all balloons
    public ArrayList<Balloon> getBalloons(){
        return balloons;
    }

    public int getCellSize(){
        return grid.getCellSize();
    }

    // Return tile from tileset given tilenum
    public TextureRegion getTileBatch(int tileNum){
        return TileSet.getTile(tileNum);
    }


    // Return num of rows
    public int getGridRows(){
        return grid.getRows();
    }

    // Returns num of cols
    public int getGridCols(){
        return grid.getCols();
    }

    // Returns value in gird, given row and col
    public int getGridValue(CellPosition pos){
        return grid.getValue(pos);
    }
}
