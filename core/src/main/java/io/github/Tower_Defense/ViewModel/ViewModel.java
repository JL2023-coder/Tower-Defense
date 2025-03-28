package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Grid.CSVReader;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.Model.Grid.Map.TileSet;

public class ViewModel {
    // Instance Variables
    BalloonFactory factory;
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    // Time between each spawn
    static float SPAWN_INTERVAL = 3;
    // Time since last spawn
    float timeSinceLastSpawn = 0;
    // MapController
    MapController mapController;

    // Public constructor
    public ViewModel(){
        this.factory = new BalloonFactory();
        this.mapController = new MapController();
    }

    // Updates game, moves balloons and spawn in
    public void update(float delta){
        spawnBalloon(delta);
        moveBalloons(delta);
    }

    // Move all balloons in list
    private void moveBalloons(float delta){
        for(Balloon b:balloons){
            b.move(delta, getBalloonDirection(b.getPosX(), b.getPosY()));
        }
    }

    private char getBalloonDirection(int posX, int posY){
        CellPosition pos = pixelToCellPositionConverter(posX, posY);
        CellPosition nextWayPoint = mapController.getNextWayPoint(pos);

        return getDirectionFromCurrrentPosAndWayPoint(pos, nextWayPoint);
    }

    private char getDirectionFromCurrrentPosAndWayPoint(CellPosition pos, CellPosition wayPoint){
        if(pos.row() > wayPoint.row()){
            return 'U';
        }
        else if(pos.row() < wayPoint.row()){
            return 'D';
        }

        else if(pos.col() < wayPoint.col()){
            return 'R';
        }
        else if(pos.col() > wayPoint.col()){
            return 'L';
        }
        else{
            throw new IllegalArgumentException("Could not get Direction");
        }
    }
    private CellPosition pixelToCellPositionConverter(int posX, int posY){
        int row = mapController.getMapRows() - (posY + mapController.getCellSize() - 1) / mapController.getCellSize() - 1;
        int col = posX / mapController.getCellSize();

        return new CellPosition(row, col);
    }

    // Spawns balloon if timeSinceLastSpawn is larger than SpawnInterval
    private void spawnBalloon(float delta){
        timeSinceLastSpawn += delta;
        if(timeSinceLastSpawn >= SPAWN_INTERVAL){
            int spawnPosX = mapController.getStartPosX();
            int spawnPosY = mapController.getStartPosY();
            balloons.add(factory.getNext(spawnPosX, spawnPosY));
            timeSinceLastSpawn = 0;
        }
    }
    // Returns list of all balloons
    public ArrayList<Balloon> getBalloons(){
        return balloons;
    }

    public int getCellSize(){
        return mapController.getCellSize();
    }

    // Return tile from tileset given tilenum
    public TextureRegion getTileBatch(int tileNum){
        return TileSet.getTile(tileNum);
    }


    // Return num of rows
    public int getMapRows(){
        return mapController.getMapRows();
    }

    // Returns num of cols
    public int getMapCols(){
        return mapController.getMapCols();
    }

    // Returns value in gird, given row and col
    public int getMapValue(CellPosition pos){
        return mapController.getMapValue(pos);
    }
}
