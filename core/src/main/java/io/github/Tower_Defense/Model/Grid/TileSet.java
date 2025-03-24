package io.github.Tower_Defense.Model.Grid;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TileSet {
    private static Texture tileSet;
    private static int colTileSet = 8;
    private static int tileSize = 32;
    
    static {
        tileSet = getImage();
    }
    
    private static Texture getImage(){
        Texture tileSet = new Texture(Gdx.files.internal("core/src/main/java/io/github/Tower_Defense/Model/Grid/src/FieldsTileset.png"));
        return tileSet;
    }

    // Returns TextureRegion, a subimage from tileSet.png, corresponding tile to tileNum
    public static TextureRegion getTile(int tileNum){
        ArrayList<Integer> tilePos = tileNumToTilePosConverter(tileNum);
            int x = tilePos.get(1) * tileSize;
            int y = tilePos.get(0) * tileSize;
    
            TextureRegion tile = new TextureRegion(tileSet, x, y, tileSize, tileSize);
        
        return tile;
    }
        
    private static ArrayList<Integer> tileNumToTilePosConverter(int tileNum){
        ArrayList<Integer> tilePostions = new ArrayList<Integer>(2);

        int rowNum = (tileNum) / colTileSet; 
        int colNum = (tileNum) % colTileSet;

        // System.out.println("tileNum = " +tileNum);
        // System.out.println("col:" + colNum + "row:" + rowNum);
        // System.out.println("--------");

        tilePostions.add(rowNum);
        tilePostions.add(colNum);

        return tilePostions;
    }
}
