package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameMenuScreen implements Screen {
    final Principal game;
    private Stage stage;
    private Texture backgroundTexture;
    private Music backgroundMusic;
    private SpriteBatch batch;

    public GameMenuScreen(Principal game) {
        this.game = game;
    }

    @Override
    public void show() {
        //fundo = new Texture("menu.png"); //  imagem menu
        backgroundTexture = new Texture("menu.png");
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("musica_fundo.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        batch = new SpriteBatch();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        // Cria um botão de texto
        TextButton textButton = new TextButton("BEM VINDO AO JOGO", skin);
        textButton.setPosition(300, 350);
        textButton.setSize(200, 50);
        // Cria um botão de texto para jogar
        TextButton playButton = new TextButton("Jogar ou Pressionar ENTER", skin);
        playButton.setPosition(300, 250);
        playButton.setSize(200, 50);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                backgroundMusic.stop();
                game.setScreen(new FirstScreen());
            }
        });
        // Cria um botão de texto para sair
        TextButton exitButton = new TextButton("Sair ou Pressionar ESC", skin);
        exitButton.setPosition(300, 150);
        exitButton.setSize(200, 50);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        stage.addActor(textButton);
        stage.addActor(playButton);
        stage.addActor(exitButton);

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1); // fundo azul escuro
        //ScreenUtils.clear(0, 0, 0.2f, 1); // fundo azul escuro
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new FirstScreen());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        backgroundTexture.dispose(); // liberta a memória da textura de fundo
        backgroundMusic.dispose(); // liberta a memória da música de fundo
        batch.dispose(); // liberta a memória do SpriteBatch
        stage.dispose(); // liberta a memória do Stage

    }
}

