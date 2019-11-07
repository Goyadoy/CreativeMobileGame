package com.creativemobile.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public final class MainScreen implements Screen {
    private final OrthographicCamera camera = Game.graphics().getMenuCamera();
    private final ShapeRenderer shape = Game.graphics().getShape();

    private final String FULL_MESSAGE_TEXT = "Lorem ipsum dolor sit amet,\n" +
            "consectetur adipiscing elit,\n" +
            "sed do eiusmod tempor incididunt\n" +
            "ut labore et dolore magna aliqua.";

    private WidgetGroup board;
    private TextArea text;
    private TextButton button;

    private Stage stage;

    public MainScreen() {
        stage = new Stage(Game.graphics().getMenuViewport(), Game.graphics().getBatch());

        board = new WidgetGroup();

        text = new TextArea("", new Skin(Gdx.files.internal("uiskin.json")));
        text.setAlignment(Align.center);
        text.getStyle().messageFontColor = Color.BLACK;
        text.setDisabled(true);
        text.getStyle().font = Game.graphics().getFont().getBitmapFont();

        button = new TextButton("start", new Skin(Gdx.files.internal("uiskin.json")));
        button.getLabel().setStyle(new Label.LabelStyle(Game.graphics().getFont().getBitmapFont(), Color.BLACK));
        button.getLabel().setColor(Color.BLACK);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (button.isDisabled()) {
                    return;
                }

                button.setDisabled(true);
                button.setTouchable(Touchable.disabled);

                text.addAction(Actions.sequence(new Action() {
                    @Override
                    public boolean act(float delta) {
                        text.setMessageText("");
                        return true;
                    }
                }, new Action() {
                    float time;

                    @Override
                    public boolean act(float delta) {
                        time += delta;
                        int chars = Math.min((int) (time * 10), FULL_MESSAGE_TEXT.length());

                        if (chars != text.getMessageText().length()) {
                            text.setMessageText(FULL_MESSAGE_TEXT.substring(0, chars));
                        }

                        return chars == FULL_MESSAGE_TEXT.length();
                    }
                }, new Action() {
                    @Override
                    public boolean act(float delta) {
                        button.setDisabled(false);
                        button.setTouchable(Touchable.enabled);
                        return true;
                    }
                }));
            }
        });

        board.addActor(text);
        board.addActor(button);

        stage.addActor(board);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);

        camera.update();

        // draw board outline
        shape.begin();
        shape.setProjectionMatrix(camera.combined);
        shape.set(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLACK);
        shape.rect(board.getX(), board.getY(), board.getWidth(), board.getHeight());
        shape.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (camera.viewportWidth > camera.viewportHeight) {
            board.setSize(camera.viewportHeight * 0.9f, camera.viewportHeight * 0.9f);
        } else {
            board.setSize(camera.viewportWidth * 0.9f, camera.viewportWidth * 0.9f);
        }

        board.setPosition(camera.position.x, camera.position.y, Align.center);

        button.setSize(board.getWidth() - 20, 100);
        button.setPosition(board.getWidth() / 2, 10, Align.bottom);

        text.setSize(board.getWidth() - 20, board.getHeight() - button.getY(Align.top) - 120);
        text.setPosition(board.getWidth() / 2, board.getHeight() - 60, Align.top);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
