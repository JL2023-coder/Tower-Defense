package io.github.Tower_Defense.Model.Entity.Balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BalloonTexture {

        
    private static TextureRegion getImage(){
        Texture balloonTexture = new Texture(Gdx.files.internal("core/src/main/java/io/github/Tower_Defense/Model/Entity/Balloon/BalloonRED.png"));
        TextureRegion bt = new TextureRegion(balloonTexture, 113, 93, 161, 175);
        return bt;
    }
    
    // Returns TextureRegion, a subimage from tileSet.png, corresponding tile to tileNum
    public static TextureRegion getBalloonTexture(){
        return getImage();
    }
}
