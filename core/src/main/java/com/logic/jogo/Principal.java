package com.logic.jogo;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Principal extends Game {

    @Override
    public void create() { // Metodo da aplicação, é chamado quando a aplicação é iniciada.
                // Initialize the game and set the first screen

        setScreen(new FirstScreen());

    }

}
