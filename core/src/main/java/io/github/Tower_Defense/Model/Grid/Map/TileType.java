package io.github.Tower_Defense.Model.Grid.Map;

public enum TileType {
    BLOCKED(37),
    START(46),
    END(38);

    private final int value;

    TileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
