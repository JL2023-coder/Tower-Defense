package io.github.Tower_Defense.Model.Entity;

public interface IBalloonFactory {
    Balloon getNext(int posX, int posY);
}
