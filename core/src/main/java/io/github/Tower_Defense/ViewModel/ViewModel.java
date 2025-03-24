package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.Grid;

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

    // Returns list of all balloons
    public ArrayList<Balloon> getBalloons(){
            return balloons;
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
}
