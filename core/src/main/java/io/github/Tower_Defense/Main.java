package io.github.Tower_Defense;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.View.View;
import io.github.Tower_Defense.ViewModel.ViewModel;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    // Instance Variables
    Grid grid;
    View view;
    ViewModel gamePanel;


    @Override
    public void create() {
        grid = new Grid(60, 34);
        ViewModel viewModel = new ViewModel();
        view = new View(viewModel);
    }

    @Override
    public void resize(int width, int height) {
        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        // Update should be based on time not frames
        float delta = Gdx.graphics.getDeltaTime();
        // Updates game
        gamePanel.update(delta);
        // Renders game
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