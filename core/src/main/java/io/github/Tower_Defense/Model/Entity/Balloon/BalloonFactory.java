package io.github.Tower_Defense.Model.Entity.Balloon;

import java.util.ArrayList;

public class BalloonFactory implements IBalloonFactory{

    @Override
    public Balloon getNext(int posX, int posY) {
        ArrayList baloonTypes = new ArrayList<String>();
        baloonTypes.add("base");
        return Balloon.newBalloon("base", posX, posY);
    }


    
}
