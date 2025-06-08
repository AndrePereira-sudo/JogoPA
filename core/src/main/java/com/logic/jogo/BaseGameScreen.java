package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public abstract class BaseGameScreen extends ScreenAdapter {

    protected Principal game;
    protected SpriteBatch batch;
    protected OrthographicCamera camera;
    protected Viewport viewport;

    protected Texture jogadorTextura;
    protected Rectangle jogador;

    protected ArrayList<Rectangle> obstaculos;

    public BaseGameScreen(Principal game, float jogadorX, float jogadorY) {
        this.game = game;
        this.jogador = new Rectangle(jogadorX, jogadorY, 32, 32);
        this.obstaculos = new ArrayList<>();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        jogadorTextura = new Texture("jogador.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        viewport = new StretchViewport(800, 600, camera);
        viewport.apply();

        criarObstaculos();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atualizarJogador(delta);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
        desenharObstaculos();
        batch.end();
    }

    protected void atualizarJogador(float delta) {

        // Movimento do jogador
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            jogador.x -= 200 * delta; // Move para a esquerda
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            jogador.x += 200 * delta; // Move para a direita
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            jogador.y += 200 * delta; // Move para cima
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            jogador.y -= 200 * delta; // Move para baixo
        }
        // Limitar o jogador ao ecrã
        if (jogador.x < 0) jogador.x = 0; // Limitar à esquerda
        if (jogador.x + jogador.width > 800) jogador.x = 800 - jogador.width; // Limitar à direita
        if (jogador.y < 0) jogador.y = 0; // Limitar ao fundo
        if (jogador.y + jogador.height > 600) jogador.y = 600 - jogador.height; // Limitar ao topo

        // Aqui você pode adicionar lógica para detectar colisões com obstáculos
        for (Rectangle obst : obstaculos) {
            if (jogador.overlaps(obst)) {
                // Colisão detectada, você pode implementar a lógica de resposta aqui
                // Por exemplo, reposicionar o jogador ou impedir o movimento
                jogador.x = Math.max(jogador.x, obst.x + obst.width); // Reposicionar o jogador para evitar sobreposição
            }
        }
        // Aqui você pode adicionar lógica para detectar colisões com inimigos
        // Por exemplo, se você tiver uma lista de inimigos, você pode iterar sobre eles e verificar colisões
        // for (Rectangle inimigo : inimigos) {
        //     if (jogador.overlaps(inimigo)) {
        //         // Colisão com inimigo, implementar lógica de resposta

        // Colisão com obstáculos

        // Limitar o jogador ao ecrã
    }
     protected void desenharObstaculos() {
        for (Rectangle obst : obstaculos) {
            batch.draw(jogadorTextura, obst.x, obst.y, obst.width, obst.height); // usa a mesma textura só como exemplo
        }
    }

    protected void criarObstaculos() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        jogadorTextura.dispose();
    }
}
