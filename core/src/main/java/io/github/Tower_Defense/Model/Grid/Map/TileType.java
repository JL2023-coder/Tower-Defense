package io.github.Tower_Defense.Model.Grid.Map;

public enum TileType {
    BLOCKED(37),
    START(0),
    END(3);

    private final int value;

    TileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
