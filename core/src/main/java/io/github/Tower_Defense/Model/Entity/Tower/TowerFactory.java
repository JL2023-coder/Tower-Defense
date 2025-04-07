package io.github.Tower_Defense.Model.Entity.Tower;

import java.util.ArrayList;

import io.github.Tower_Defense.Model.Grid.CellPosition;

public class TowerFactory{

    public Tower addTower(String towerType, CellPosition pos){  
        ArrayList<String> towerTypes = new ArrayList<String>();
        towerTypes.add("base");
        return Tower.newTower("base", pos);
    }


    
}