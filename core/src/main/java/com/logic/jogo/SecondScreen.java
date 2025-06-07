package com.logic.jogo;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Este é o segundo ecrã do jogo, onde a nova dimensão é apresentada
// É implementado a interface Screen do libGDX para gerenciar o ciclo de vida do ecrã
// É necessário importar as classes do libGDX para manipular gráficos e renderização

public class SecondScreen implements Screen {
    //SpriteBatch batch;
    // Texture background;
    private SpriteBatch batch;
    private Texture background;
    @Override
    public void show() {
        batch   = new SpriteBatch();
        background = new Texture("dimensao2.png"); // imagem da nova dimensão ou ecran}
    }
        @Override
    public void render(float delta) {

        // Limpar a tela com uma cor de fundo
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.5f, 1);// Cor de fundo azul
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);// Limpar o buffer de cor

        // Desenhar o fundo da nova dimensão

            batch.begin();
        // Desenhar o fundo da nova dimensão
       batch.draw(background, 0, 0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
