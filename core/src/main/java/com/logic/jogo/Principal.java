package com.logic.jogo;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Principal extends Game {

    @Override
    public void create() {
        System.out.println(">> Entrou no create() de MainGame");
       // This is the main method of the application. It is called when the application is launched.
        // You can initialize your application here.
        // Initialize the game and set the first screen


        setScreen(new FirstScreen());
        // erro abre e fecha de imediato
        //setScreen(new FirstScreen());

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
