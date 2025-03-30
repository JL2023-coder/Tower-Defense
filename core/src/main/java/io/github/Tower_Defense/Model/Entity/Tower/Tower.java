package io.github.Tower_Defense.Model.Entity.Tower;

import com.badlogic.gdx.math.Rectangle;

import io.github.Tower_Defense.Model.Entity.Balloon.Balloon;
import io.github.Tower_Defense.Model.Grid.CellPosition;

public class Tower extends Rectangle{
    // Instance Variables
    String towerType;
    float attackSpeed;
    int attatckDamage;
    CellPosition position;

    private Tower(String towerType, float attackSpeed, int attatckDamage, CellPosition position){
        this.towerType = towerType;
        this.attackSpeed = attackSpeed;
        this.attatckDamage = attatckDamage;
        this.position = position;
    }

    public Tower addTower(String towerType, CellPosition position){
        float attackSpeed;
        int attatckDamage;
        switch (towerType) {
            case "base":
                attackSpeed = 1f;
                attatckDamage = 100;
                break;
            default:
                throw new IllegalArgumentException("Uknown type");
        }

        return new Tower(towerType, attackSpeed, attatckDamage, position);
    }


}
