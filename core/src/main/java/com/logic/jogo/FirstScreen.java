package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable.draw;
import com.badlogic.gdx.math.Rectangle;
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
    ArrayList<Rectangle> obstaculos;
    // Array de inimigos
    ArrayList<Rectangle> inimigos;
    // Array de jogadores
    ArrayList<Rectangle> jogadores;
    // Array de texturas
    ArrayList<Texture> texturas;
    // Array de sons
    ArrayList<Object> sons;



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

        jogadorTextura = new Texture("jogador.png");
        inimigoTextura = new Texture("inimigo.png");
        obstaculoTextura = new Texture("obstaculo.png");
        backgroundTexture = new Texture("background.png");

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
        // Inicializar inimigos
        inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = 200 + i * 100;
            inimigo.y = 200 + i * 50;
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
        }
        // Inicializar jogadores
        jogadores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Rectangle jogador = new Rectangle();
            jogador.x = 50 + i * 100;
            jogador.y = 50 + i * 50;
            jogador.width = 32;
            jogador.height = 32;
            jogadores.add(jogador);
        }
        // Inicializar texturas
        texturas = new ArrayList<>();
        texturas.add(jogadorTextura);
        texturas.add(inimigoTextura);
        texturas.add(obstaculoTextura);
        // Inicializar sons
        sons = new ArrayList<>();
        // Aqui você pode carregar sons, por exemplo:
        // sons.add(Gdx.audio.newSound(Gdx.files.internal("som.mp3")));
        // Inicializar obstáculos
        // Inicializar obstáculos



        // Criar obstáculos
        obstaculos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Rectangle obstaculo = new Rectangle();
            obstaculo.x = 50 + (i % 4) * 150;
            obstaculo.y = 400 - (i / 4) * 150;
            obstaculo.width = 32;
            obstaculo.height = 32;
            //LIXO try {
            //    obstaculos.wait();
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}
            obstaculos.add(obstaculo);
        }

    }

    @Override
    public void render(float delta) {

        // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // novo Libgdx game site
        imput();
        logic();
        draw();


        // Atualizar lógica do jogo


        camera.update();
        loteDesenho.setProjectionMatrix(camera.combined);

        /*LIXO        loteDesenho.begin();

        // Desenhar jogador e inimigo
        loteDesenho.draw(jogadorTextura, jogador.x, jogador.y);
        loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y);

        // Desenhar obstáculos
        for (Rectangle o : obstaculos) {
            loteDesenho.draw(obstaculoTextura, o.x, o.y);
        }

        loteDesenho.end();      */
    }

    private void draw() {

        ScreenUtils.clear(com.badlogic.gdx.graphics.Color.BLACK);
        Viewport viewport = new com.badlogic.gdx.utils.viewport.StretchViewport(800, 600, camera);
        viewport.apply();
        loteDesenho.setProjectionMatrix(viewport.getCamera().combined);
        loteDesenho.begin();

        // Desenhar fundo
        loteDesenho.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        // Desenhar jogador
        loteDesenho.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
        // Desenhar inimigo
        loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
        // Desenhar obstáculos
        for (Rectangle obstaculo : obstaculos) {
            loteDesenho.draw(obstaculoTextura, obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }
        // Desenhar inimigos
        for (Rectangle inimigo : inimigos) {
            loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
        }
        // Desenhar jogadores
        for (Rectangle jogador : jogadores) {
            loteDesenho.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
        }
        // LIXO   ja feito Desenhar texturas
        //for (Texture textura : texturas) {
          //  loteDesenho.draw(textura, 0, 0);
       // }
        // Desenhar sons (se necessário, mas geralmente não se desenham sons)
        // for (Object som : sons) {
        //     loteDesenho.draw(som, 0, 0);
        // }
        // Finalizar lote de desenho

        loteDesenho.end();

    }

    private void logic() {
    }


    private void imput() {
    }


    // Libertar recursos
    //  jogadorTextura.dispose();
    //   inimigoTextura.dispose();
    //   obstaculoTextura.dispose();
    //  loteDesenho.dispose();


    // Draw your screen here. "delta" is the time since last render in seconds.

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        Viewport viewport = new com.badlogic.gdx.utils.viewport.StretchViewport(800, 600, camera);
        viewport.update(width, height, true);
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
        // Dispose of textures
        jogadorTextura.dispose();
        inimigoTextura.dispose();
        obstaculoTextura.dispose();
        backgroundTexture.dispose();
        // Dispose of sprite batch
        loteDesenho.dispose();
        // Dispose of other resources if necessary
        for (Texture textura : texturas) {
            textura.dispose();
        }
        // Dispose of sounds if necessary
        for (Object som : sons) {
            // Assuming som is a Sound object, you would call som.dispose() if it were a Sound
            // som.dispose(); // Uncomment if som is a Sound object

        }


    }

}
