package com.creativemobile.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.Hinting;
import com.badlogic.gdx.utils.Disposable;

public final class Font implements Disposable {
    private BitmapFont font;
    private int size;

    public Font(int size) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = size;
        parameter.borderStraight = false;
        parameter.hinting = Hinting.Medium;
        parameter.gamma = 3f;
        parameter.genMipMaps = false;
        parameter.minFilter = TextureFilter.Linear;
        parameter.magFilter = TextureFilter.Linear;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Arial.ttf"));
        font = generator.generateFont(parameter);

        font.setColor(Color.WHITE);
        font.getData().missingGlyph = font.getData().getGlyph(' ');
        font.setUseIntegerPositions(false);

        generator.dispose();
    }

    public BitmapFont getBitmapFont() {
        return font;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void dispose() {
        if (font != null) {
            font.dispose();
            font = null;
        }
    }
}
