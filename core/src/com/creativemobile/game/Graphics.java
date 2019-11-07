package com.creativemobile.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public final class Graphics {
    private static final float MENU_WIDTH = 720;
    private static final float MENU_HEIGHT = 720;

    private static final Color BACKGROUND = new Color(0xd3e2f2ff);

    private ExtendViewport menuViewport;
    private OrthographicCamera menuCamera;

    private SpriteBatch batch;
    private ShapeRenderer shape;
    private Font font;

    private float menuPpuX, menuPpuY;

    Graphics() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);

        menuCamera = new OrthographicCamera(Graphics.MENU_WIDTH, Graphics.MENU_HEIGHT);
        menuViewport = new ExtendViewport(menuCamera.viewportWidth, menuCamera.viewportHeight, menuCamera);
        menuViewport.apply();

        font = new Font(42);

        Gdx.gl.glClearColor(BACKGROUND.r, BACKGROUND.g, BACKGROUND.b, BACKGROUND.a);
        Gdx.gl.glLineWidth(1);
    }

    public void updateSize(float width, float height) {
        menuViewport.update((int) width, (int) height);

        final float menuScale = height / width > getMenuHeight() / getMenuWidth() ? width / getMenuWidth()
                : height / getMenuHeight();

        menuCamera.viewportWidth = getMenuWidth() + (width - getMenuWidth() * menuScale) / menuScale;
        menuCamera.viewportHeight = getMenuHeight() + (height - getMenuHeight() * menuScale) / menuScale;
        menuViewport.setWorldSize(menuCamera.viewportWidth, menuCamera.viewportHeight);

        menuCamera.position.x = menuCamera.viewportWidth / 2;
        menuCamera.position.y = menuCamera.viewportHeight / 2;

        menuPpuX = width / menuCamera.viewportWidth;
        menuPpuY = height / menuCamera.viewportHeight;
    }

    private float getMenuWidth() {
        return MENU_WIDTH;
    }

    private float getMenuHeight() {
        return MENU_HEIGHT;
    }

    public float getMenuPpuX() {
        return menuPpuX;
    }

    public float getMenuPpuY() {
        return menuPpuY;
    }

    public Batch getBatch() {
        return batch;
    }

    public ShapeRenderer getShape() {
        return shape;
    }

    public ExtendViewport getMenuViewport() {
        return menuViewport;
    }

    public OrthographicCamera getMenuCamera() {
        return menuCamera;
    }

    public Font getFont() {
        return font;
    }


    public void dispose() {
        if (batch != null) {
            batch.dispose();
            batch = null;
        }

        if (shape != null) {
            shape.dispose();
            shape = null;
        }

        if (font != null) {
            font.dispose();
            font = null;
        }
    }
}
