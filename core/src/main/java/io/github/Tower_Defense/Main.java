package io.github.Tower_Defense;

import com.badlogic.gdx.ApplicationListener;

import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.View.View;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    // Instance Variables
    Grid grid;
    View view;


    @Override
    public void create() {
        grid = new Grid(60, 34);
        view = new View(grid);
    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        view.renderGame();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        view.dispose();
    }
}