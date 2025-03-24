package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.CSVReader;
import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.Model.Grid.TileSet;

public class ViewModel {
    // Instance Variables
    BalloonFactory factory;
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    Grid grid;
    // Time between each spawn
    static float SPAWN_INTERVAL = 3;
    // Time since last spawn
    float timeSinceLastSpawn = 0;

    // Public constructor
    public ViewModel(Grid grid){
        this.factory = new BalloonFactory();
        this.grid = grid;

        // Add values to grid
        initializeMap();
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
            balloons.add(factory.getNext());
            timeSinceLastSpawn = 0;
        }
    }
    // Returns list of all balloons
    public ArrayList<Balloon> getBalloons(){
        return balloons;
    }

    // Returns a 2d array with map values
    public List<List<Integer>> getMap(){
        return CSVReader.readCSV("core/src/main/java/io/github/Tower_Defense/Model/Grid/src/map1.csv");
    }

    // Add values to grid
    private void initializeMap() {
        List<List<Integer>> map = getMap();
        // Update grid to match map size
        int rows = map.size();
        int cols = map.get(0).size();
        grid.setRows(rows);
        grid.setCols(cols);
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int value = map.get(row).get(col);
                grid.setValue(value, row, col);
            }
        }
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
    public int getGridValue(int row, int col){
        return grid.getValue(row, col);
    }
}
