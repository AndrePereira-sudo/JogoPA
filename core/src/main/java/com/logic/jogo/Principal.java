package com.logic.jogo;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Principal extends Game {

    @Override
    public void create() { // Metodo da aplicação, é chamado quando a aplicação é iniciada.
                // Initialize the game and set the first screen


        setScreen(new FirstScreen());

      /* apenas necessário se for class abstrat  firstsreen
              // Initialize the game and set the first screen

        setScreen(new FirstScreen() {
            @Override
            public void render(float delta) {
                // Implement rendering logic here
            }

            @Override
            public void resize(int width, int height) {
                // Handle resizing logic here
            }

            @Override
            public void show() {
                // Initialize resources here
            }

            @Override
            public void hide() {
                // Handle logic for when the screen is hidden here
            }

            @Override
            public void pause() {
                // Handle logic for pausing here
            }

            @Override
            public void resume() {
                // Handle logic for resuming here
            }

            @Override
            public void dispose() {
                // Clean up resources here
            }
        });
        */
        // Initialize the game and set the first screen
    }
}
