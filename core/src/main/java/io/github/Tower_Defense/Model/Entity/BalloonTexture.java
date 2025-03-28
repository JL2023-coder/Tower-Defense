package io.github.Tower_Defense.Model.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BalloonTexture {

        
    private static Texture getImage(){
        Texture balloonTexture = new Texture(Gdx.files.internal("core/src/main/java/io/github/Tower_Defense/Model/Entity/BalloonRED.png"));
        return balloonTexture;
    }
    
    // Returns TextureRegion, a subimage from tileSet.png, corresponding tile to tileNum
    public static Texture getBalloonTexture(){
        return getImage();
    }
}
