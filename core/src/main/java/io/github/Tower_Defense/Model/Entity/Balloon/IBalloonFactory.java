package io.github.Tower_Defense.Model.Entity.Balloon;

public interface IBalloonFactory {
    Balloon getNext(int posX, int posY);
}
