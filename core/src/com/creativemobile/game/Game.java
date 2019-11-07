package com.creativemobile.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public final class Game implements ApplicationListener {
    private static final Game INSTANCE = new Game();

    private Graphics graphics;
    private MainScreen screen;

    private Game() {
    }


    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_INFO);

        graphics = new Graphics();
        screen = new MainScreen();

        Gdx.input.setInputProcessor(screen.getStage());
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        // delta = 0.01666f; - 60 fps
        if (delta > 0.05f) { // 20 fps min
            delta = 0.05f;
        }

        screen.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        graphics.updateSize(width, height);
        screen.show();
        screen.resize(width, height);
    }

    @Override
    public void pause() {
        screen.pause();
    }

    @Override
    public void resume() {
        screen.resume();
    }

    @Override
    public void dispose() {
        screen.hide();
        screen.dispose();
        graphics.dispose();
    }

    public static Graphics graphics() {
        return getInstance().graphics;
    }

    public static Game getInstance() {
        return INSTANCE;
    }
}
