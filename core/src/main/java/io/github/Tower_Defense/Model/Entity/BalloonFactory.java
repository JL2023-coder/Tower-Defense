package io.github.Tower_Defense.Model.Entity;

import java.util.ArrayList;

public class BalloonFactory implements IBalloonFactory{

    @Override
    public Balloon getNext() {
        ArrayList baloonTypes = new ArrayList<String>();
        baloonTypes.add("base");
        return Balloon.newBalloon("base");
    }


    
}
