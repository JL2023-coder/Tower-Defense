package io.github.Tower_Defense.Model;

import java.util.ArrayList;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.Grid;

public class GamePanel {
    BalloonFactory factory;
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    Grid grid;
    // Time between each spawn
    static float SPAWN_INTERVAL = 3;
    float timeSinceLastSpawn = 0;

    public GamePanel(){
        this.factory = new BalloonFactory();
    }

    public ArrayList<Balloon> getBalloons(){
            return balloons;
    }

    public void startGame(){
        balloons.add(factory.getNext());
    }


    public void update(float delta){
        spawnBalloon(delta);
        moveBalloons(delta);
    }

    private void moveBalloons(float delta){
        for(Balloon b:balloons){
            b.move(delta);
        }
    }

    // Spawns balloon if timeSinceLastSpawn is largert than SpawnInterval
    private void spawnBalloon(float delta){
        timeSinceLastSpawn += delta;
        if(timeSinceLastSpawn >= SPAWN_INTERVAL){
            balloons.add(factory.getNext());
            timeSinceLastSpawn = 0;
        }
    }
}
