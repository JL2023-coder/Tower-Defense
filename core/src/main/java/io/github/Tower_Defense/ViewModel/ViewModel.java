package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.Grid;

public class ViewModel {
    BalloonFactory factory;
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    Grid grid;
    // Time between each spawn
    static float SPAWN_INTERVAL = 3;
    float timeSinceLastSpawn = 0;

    public ViewModel(Grid grid){
        this.factory = new BalloonFactory();
        this.grid = grid;
    }

    public int getGridRows(){
        return grid.getRows();
    }

    public int getGridCols(){
        return grid.getCols();
    }

    public int getGridValue(int row, int col){
        return grid.getValue(row, col);
    }

    public ArrayList<Balloon> getBalloons(){
            return balloons;
    }

    public void update(float delta){
        spawnBalloon(delta);
        moveBalloons(delta);
    }

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
