package io.github.Tower_Defense.Model.Entity.Tower;

import com.badlogic.gdx.math.Rectangle;

import io.github.Tower_Defense.Model.Entity.Balloon.Balloon;
import io.github.Tower_Defense.Model.Grid.CellPosition;

public class Tower {
    // Instance Variables
    String towerType;
    float attackSpeed;
    int attackDamage;
    CellPosition pos;
    int width = 32;
    int height = 32;

    private Tower(String towerType, float attackSpeed, int attackDamage, CellPosition pos){
        this.towerType = towerType;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;
        this.pos = pos;
    }

    public static Tower newTower(String towerType, CellPosition pos){
        float attackSpeed;
        int attackDamage;
        switch (towerType) {
            case "base":
                attackSpeed = 1f;
                attackDamage = 100;
                break;
            default:
                throw new IllegalArgumentException("Uknown type");
        }

        return new Tower(towerType, attackSpeed, attackDamage, pos);
    }

    public CellPosition getCellPosition(){
        return pos;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    
}
