package com.logic.jogo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Principal extends Game {
    public static Batch batch;
    //BitmapFont font = null; // BitmapFont para desenhar texto na tela

    //public static Object getInstance() {
    //    return new Principal();
   // }
    public Principal() {
        // Construtor da classe Principal, onde podemos inicializar recursos do jogo
    }


    @Override
    public void create() {
        batch = new SpriteBatch();
        //font = new BitmapFont();
        this.setScreen(new GameMenuScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        //font.dispose();
        if (getScreen() != null) {
            getScreen().dispose(); // Dispose the current screen if it exists
        }

    }
}
