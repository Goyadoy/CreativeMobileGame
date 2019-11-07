package com.creativemobile.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.creativemobile.game.Game;

public class DesktopLauncher {

    public static void main(String[] arg) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "Creative Mobile Test Game";
        config.vSyncEnabled = true;
        config.useGL30 = false;
        config.backgroundFPS = 30;
        config.samples = 0;
        config.fullscreen = false;
        config.useHDPI = true;

        config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;

        config.x = config.width / 2;
        config.y = config.height / 2 - config.height / 26;

        config.width *= 0.7;
        config.height = (int) (config.width / 1.7777777777);

        config.x -= config.width / 2;
        config.y -= config.height / 2;

        new LwjglApplication(Game.getInstance(), config);
    }
    
}
