package com.logic.jogo;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;
import java.lang.reflect.Array;
import static javax.print.attribute.standard.MediaSizeName.C;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    SpriteBatch loteDesenho;

    // Carregar texturas

    Texture jogadorTextura = new Texture("jogador.png");
    Texture inimigoTextura;
    Texture obstaculoTextura;
    Texture backgroundTexture;
    OrthographicCamera camera;
    Rectangle jogador;
    Rectangle inimigo;
    Rectangle obstaculo;
    // Array de obstáculos
    Array obstaculos;
    // Array de inimigos
    Array inimigos;
    // Array de jogadores
    Array jogadores;
    // Array de texturas
    Array texturas;
    // Array de sons
    Array sons;



    // This is the main method of the application. It is called when the application is launched.
    // You can initialize your application here.



    // This is the constructor of the screen. You can initialize your screen here.
    @Override
    public void show() {
        // Prepare your screen here.
        // Inicializar lote de desenho
        loteDesenho = new SpriteBatch();

        // Criar câmara ortográfica
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);


        jogadorTextura = new Texture(C:\Users\carlo\JogoPA\JogoPA"jogador.png");
        inimigoTextura = new Texture(assets;"inimigo.png");
        obstaculoTextura = new Texture(assets;"obstaculo.png");
        backgroundTexture = new Texture(assets;"background.png");

        // For example, you can set up your game objects, load assets, etc.

        // Inicializar jogador
        jogador = new Rectangle();
        jogador.x = 100;
        jogador.y = 100;
        jogador.width = 32;
        jogador.height = 32;

        // Inicializar inimigo
        inimigo = new Rectangle();
        inimigo.x = 500;
        inimigo.y = 300;
        inimigo.width = 32;
        inimigo.height = 32;

        // Criar obstáculos
        obstaculos = new Array<>();
        for (int i = 0; i < 8; i++) {
            Rectangle obstaculo = new Rectangle();
            obstaculo.x = 50 + (i % 4) * 150;
            obstaculo.y = 400 - (i / 4) * 150;
            obstaculo.width = 32;
            obstaculo.height = 32;
            try {
                obstaculos.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void render(float delta) {

                // Limpar o ecrã
            Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            camera.update();
            loteDesenho.setProjectionMatrix(camera.combined);

            loteDesenho.begin();

            // Desenhar jogador e inimigo
            loteDesenho.draw(jogadorTextura, jogador.x, jogador.y);
            loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y);

            // Desenhar obstáculos
            for (Rectangle o : obstaculos) {
                loteDesenho.draw(obstaculoTextura, o.x, o.y);
            }

            loteDesenho.end();
        }


             // Libertar recursos
            jogadorTextura.dispose();
            inimigoTextura.dispose();
            obstaculoTextura.dispose();
            loteDesenho.dispose();
        }
    }


        // Draw your screen here. "delta" is the time since last render in seconds.
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
