package io.github.Tower_Defense;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import io.github.Tower_Defense.Model.Grid.Grid;
import io.github.Tower_Defense.Model.Grid.Map.Map;
import io.github.Tower_Defense.View.View;
import io.github.Tower_Defense.ViewModel.MapController;
import io.github.Tower_Defense.ViewModel.ViewModel;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main implements ApplicationListener {
    // Instance Variables
    Grid grid;
    Map map;
    MapController mapController;
    View view;
    ViewModel viewModel;


    @Override
    public void create() {
        map = new Map(60, 34);
        mapController = new MapController(map);
        viewModel = new ViewModel(mapController);
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
        viewModel.update(delta);
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