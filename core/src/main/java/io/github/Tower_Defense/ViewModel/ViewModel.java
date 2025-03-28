package io.github.Tower_Defense.ViewModel;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import io.github.Tower_Defense.Model.Entity.Balloon;
import io.github.Tower_Defense.Model.Entity.BalloonFactory;
import io.github.Tower_Defense.Model.Entity.BalloonRenderData;
import io.github.Tower_Defense.Model.Entity.BalloonTexture;
import io.github.Tower_Defense.Model.Grid.CellPosition;
import io.github.Tower_Defense.Model.Grid.Map.TileSet;

public class ViewModel {
    // Instance Variables
    BalloonFactory factory;
    // List of balloons
    ArrayList<Balloon> balloons = new ArrayList<Balloon>();
    // Map to store current direction for each balloon
    private Map<Balloon, Character> balloonDirections = new HashMap<>();
    // Time between each spawn
    static float SPAWN_INTERVAL = 0.5f;
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
        char direction = getDirectionFromCurrrentPosAndWayPoint(pos, nextWayPoint);
        
        // Find the balloon and update/return its direction
        for(Balloon b : balloons) {
            if(b.getPosX() == posX && b.getPosY() == posY) {
                if (isFullyInCell(posX, posY)) {
                    balloonDirections.put(b, direction);
                }
                return balloonDirections.getOrDefault(b, direction);
            }
        }
        
        return direction;
    }

    // Check if balloon is fully in cell
    private boolean isFullyInCell(int posX, int posY){
        return posX % mapController.getCellSize() == 0 && posY % mapController.getCellSize() == 0;
    }

    private char getDirectionFromCurrrentPosAndWayPoint(CellPosition pos, CellPosition wayPoint){
        // Handle vertical movement first
        if(pos.row() > wayPoint.row()){
            return 'U';
        }
        else if(pos.row() < wayPoint.row()){
            return 'D';
        }
        // Only handle horizontal movement if we're on the correct row
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
        // Back to simple conversion
        int row = mapController.getMapRows() - (posY / mapController.getCellSize());
        int col = posX / mapController.getCellSize();
        
        return new CellPosition(row, col);
    }

    // Spawns balloon if timeSinceLastSpawn is larger than SpawnInterval
    private void spawnBalloon(float delta){
        timeSinceLastSpawn += delta;
        if(timeSinceLastSpawn >= SPAWN_INTERVAL){
            int spawnPosX = mapController.getStartPosX();
            int spawnPosY = mapController.getStartPosY();
            Balloon newBalloon = factory.getNext(spawnPosX, spawnPosY);
            balloons.add(newBalloon);
            // Initialize direction for new balloon
            balloonDirections.put(newBalloon, 'D'); // or whatever initial direction
            timeSinceLastSpawn = 0;
        }
    }


    // Returns list of all balloons
    public ArrayList<BalloonRenderData> getBalloonsRenderData(){
        ArrayList<BalloonRenderData> balloonsRenderData = new ArrayList<>();
        for(Balloon b : balloons){
            int x = b.getPosX();
            int y = b.getPosY();
            int width = b.getWidth();
            int height = b.getHeight();

            balloonsRenderData.add(new BalloonRenderData(x, y, width, height, getBalloonTexture()));
        }

        return balloonsRenderData;
    }

    public int getCellSize(){
        return mapController.getCellSize();
    }

    // Return tile from tileset given tilenum
    public TextureRegion getTileBatch(int tileNum){
        return TileSet.getTile(tileNum);
    }

    // Return tile from tileset given tilenum
    public Texture getBalloonTexture(){
        return BalloonTexture.getBalloonTexture();
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
