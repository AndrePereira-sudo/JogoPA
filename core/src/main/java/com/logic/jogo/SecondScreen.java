
package com.logic.jogo;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;

// Este é o segundo ecrã do jogo, onde a nova dimensão é apresentada

public class SecondScreen implements Screen {
    // Atributos necessários para o segundo ecrã
        // Texture background;
    private SpriteBatch batch;
    private Texture background;
    private Texture jogadorTextura;
    private Rectangle jogador;

    public SecondScreen(int jogadorX, int jogadorY) {
        jogador = new Rectangle(jogadorX, jogadorY, 32, 32);
    }
    @Override
    public void show() {
        // Inicializar o SpriteBatch e as texturas necessárias para o segundo ecrã

        batch   = new SpriteBatch();
        background = new Texture("dimensao2.png"); // imagem da nova dimensão ou ecran}
        jogadorTextura = new Texture("jogador.png");

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
       batch.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
       batch.end();
    }

   @Override
    public void resize(int width, int height) {

   }
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
