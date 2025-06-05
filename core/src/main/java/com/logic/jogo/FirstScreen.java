package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import static com.badlogic.gdx.Input.Keys.G;
import static com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable.draw;
import com.badlogic.gdx.math.Rectangle;
import static javax.print.attribute.standard.MediaSizeName.C;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
    SpriteBatch loteDesenho;

    // Carregar texturas
    Texture jogadorTextura = new Texture("jogador.png");
    Texture inimigoTextura;
    Texture obstaculoTextura;
    Texture backgroundTexture;
    OrthographicCamera camera;
    Viewport viewport;
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
    ArrayList<Sound> sons;
    private Sound somColisao;
    private Music musicaFundo;

    // This is the main method of the application. It is called when the application is launched.
    // You can initialize your application here.

    // This is the constructor of the screen. You can initialize your screen here.
    @Override
    public void show() {
        musicaFundo = Gdx.audio.newMusic(Gdx.files.internal("musica_fundo.mp3")); // Substitua pelo nome do seu arquivo
        musicaFundo.setLooping(true); // Para tocar continuamente
        musicaFundo.setVolume(0.5f); // Volume entre 0.0 e 1.0
        musicaFundo.play();

        // Prepare your screen here.
        // Inicializar lote de desenho
        loteDesenho = new SpriteBatch();

        // Criar câmara ortográfica
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        viewport = new StretchViewport(800, 600, camera);
        viewport.apply();

        // Inicializar texturas
        // texturas = new ArrayList<>();
        // Carregar texturas
        // Certifique-se de que as texturas estão no diretório correto
        // Gdx.files.internal("caminho/para/sua/textura.png")
        // Aqui você pode carregar as texturas necessárias
        // Exemplo de carregamento de texturas


        jogadorTextura = new Texture("jogador.png");
        inimigoTextura = new Texture("inimigo.png");
        obstaculoTextura = new Texture("obstaculo.png");
        backgroundTexture = new Texture("background.png");

        // For example, you can set up your game objects, load assets, etc.

        // Inicializar jogador
        jogador = new Rectangle();
        jogador.x = 10;
        jogador.y = 10;
        jogador.width = 32;
        jogador.height = 32;


        // Inicializar inimigo
        inimigo = new Rectangle();
        inimigo.x = 500;
        inimigo.y = 300;
        inimigo.width = 32;
        inimigo.height = 32;

        // Inicializar inimigos
        /*
        inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = 200 + i * 100;
            inimigo.y = 200 + i * 50;
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
            System.out.println(">> adiciona inimigo criados");
        }
        */

        inimigos = Inimigo.criarInimigos();
//        Inimigo inimigo = new Inimigo();
        // Inicializar jogador na classe Jogador
        jogador = Jogador.criarJogador();
       //  Jogador jogador = new Jogador();
        // Aqui você pode inicializar os jogadores, por exemplo:




        /*
        // Inicializar jogadores
        jogadores = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Rectangle jogador = new Rectangle();
            jogador.x = 50 + i * 100;
            jogador.y = 50 + i * 50;
            jogador.width = 32;
            jogador.height = 32;
            jogadores.add(jogador);
            System.out.println(">> Jogador adicionado");
        }*/

        // Inicializar texturas
       texturas = new ArrayList<>();
        texturas.add(jogadorTextura);
        texturas.add(inimigoTextura);
        texturas.add(obstaculoTextura);

        // Inicializar sons
        sons = new ArrayList<>();
        // Aqui você pode carregar sons, por exemplo:
       sons.add(Gdx.audio.newSound(Gdx.files.internal("laser.mp3")));
       // System.out.println(">> Sons iniciados");
        Sound somColisao = Gdx.audio.newSound(Gdx.files.internal("Colisao.mp3"));
        sons = new ArrayList<>();
        sons.add(somColisao);
        System.out.println(">> Som de colisão carregado");

        //Inicializar obstáculos
        // Criar obstáculos
        obstaculos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Rectangle obstaculo = new Rectangle();
            obstaculo.x = 50 + (i % 4) * 150;
            obstaculo.y = 400 - (i / 4) * 150;
            obstaculo.width = 32;
            obstaculo.height = 32;
            obstaculos.add(obstaculo);
        }


        // Configurar lote de desenho
        loteDesenho.setProjectionMatrix(camera.combined);
        System.out.println(">> Lote de desenho configurado");
        // Definir cor de fundo
        Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        System.out.println(">> Cor de fundo definida");
        // Limpar o ecrã
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        System.out.println(">> Ecrã limpo");
        // Configurar a câmara
        camera.setToOrtho(false, 800, 600);
        System.out.println(">> Câmara configurada");
        // Atualizar a câmara
        camera.update();
        System.out.println(">> Câmara atualizada");
        // Configurar a viewport
        viewport = new StretchViewport(800, 600, camera);
        System.out.println(">> Viewport configurada");
        // Aplicar a viewport
        viewport.apply();
        System.out.println(">> Viewport aplicada");
        // Configurar o lote de desenho
        loteDesenho.setProjectionMatrix(viewport.getCamera().combined);
        System.out.println(">> Lote de desenho configurado com a câmara da viewport");

    }
// This method is called every frame to render the screen.

    @Override
    public void render(float delta) {

        //   Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Renderizar o ecrã
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
         Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta

                // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // novo Libgdx game site
        imput();
        logic();
        draw();


        // Atualizar lógica do jogo
      viewport.apply();
        // Atualizar câmara

      camera.update();
      loteDesenho.setProjectionMatrix(camera.combined);

      loteDesenho.begin();
      loteDesenho.draw(jogadorTextura, 32, 32);

      loteDesenho.end();
    }

    private void draw() {
// Desenhar o ecrã
        // Limpar o ecrã
       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
       // Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Limpar o ecrã
        // Gdx.gl.glClearColor(0, 0, 0, 1);
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
       // ScreenUtils.clear(com.badlogic.gdx.graphics.Color.BLACK);

        // Aplicar a viewport
        ScreenUtils.clear(Color.BLACK);
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
        for (Rectangle obstaculo : obstaculos) { // Verificar se "obstaculos" está inicializado e não é null
            loteDesenho.draw(obstaculoTextura, obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }

        // Desenhar inimigos  cortado 29-05-2025
       for (Rectangle inimigo : inimigos) {
         loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
       }
        // Desenhar jogadores
        //for (Rectangle jogador : jogadores) {
            //loteDesenho.draw(jogadorTextura, jogador.x, jogador.y, jogador.width, jogador.height);
        //}



        // Finalizar lote de desenho
        loteDesenho.end();

    }

    private void logic() {
        // Processar lógica do jogo
        // lógica do jogo, como movimentação de inimigos, colisões, etc.
        //  mover o inimigo em direção ao jogador
        for (Rectangle inimigo : inimigos) {
            if (inimigo.x < jogador.x) {
                inimigo.x += 100 * Gdx.graphics.getDeltaTime(); // Mover para a direita
            } else if (inimigo.x > jogador.x) {
                inimigo.x -= 100 * Gdx.graphics.getDeltaTime(); // Mover para a esquerda
            }
            if (inimigo.y < jogador.y) {
                inimigo.y += 100 * Gdx.graphics.getDeltaTime(); // Mover para cima
            } else if (inimigo.y > jogador.y) {
                inimigo.y -= 100 * Gdx.graphics.getDeltaTime();
    }
        }
        // Verificar colisões com obstáculos
        checkCollisions();
    }


    private void imput() {
        // Processar entrada do jogador
        float speed = 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) jogador.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) jogador.x += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) jogador.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) jogador.y -= speed;

        // Verificar se o jogador clicou com o mouse
        // Obter a posição do mouse e converter para coordenadas do mundo
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);

        // Mover o jogador em direção à posição do mouse
        // Se o mouse estiver pressionado, mover o jogador em direção à posição do mouse
        if (Gdx.input.isTouched()) {
        jogador.x += Math.signum(mousePos.x - jogador.x) * speed;
        jogador.y += Math.signum(mousePos.y - jogador.y) * speed;
        }
    }

    // Verificar colisões com obstáculos
    // Aqui você pode implementar a lógica de colisão entre o jogador e os obstáculos
    // Por exemplo, se o jogador colidir com um obstáculo, você pode empurrá-lo para trás ou impedir o movimento
    private void checkCollisions() {

        long play = sons.get(0).play();
       // somColisao.play();

        for (Rectangle obstaculo : obstaculos) {
            if (jogador.overlaps(obstaculo)) {
                // Resolver colisão (ex: empurrar jogador para trás)
                if (jogador.x < obstaculo.x) {
                    jogador.x = obstaculo.x - jogador.width; // Empurrar para a esquerda
                } else if (jogador.x > obstaculo.x + obstaculo.width) {
                    jogador.x = obstaculo.x + obstaculo.width; // Empurrar para a direita
                }
                if (jogador.y < obstaculo.y) {
                    jogador.y = obstaculo.y - jogador.height; // Empurrar para cima
                } else if (jogador.y > obstaculo.y + obstaculo.height) {
                    jogador.y = obstaculo.y + obstaculo.height; // Empurrar para baixo
                }

            }
        }
    }

    // This method is called when the screen is resized.

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        System.out.println(">> Entrou no resize() com largura: " + width + " e altura: " + height);
        // Limpar o ecrã
        Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Atualizar a viewport com a nova largura e altura
        viewport.update(width, height, true);
        viewport = new StretchViewport(width, height, camera);
        // Aplicar a viewport
        viewport.apply();
        // Atualizar a câmara
        camera.setToOrtho(false, width, height);
        // Atualizar a câmara com a nova largura e altura
        camera.update();
        // Atualizar o lote de desenho
        loteDesenho.setProjectionMatrix(camera.combined);

        viewport.update(width, height, true);

        // Atualizar a viewport
        camera.setToOrtho(false, width, height);
        viewport.update(width, height);

        // Atualizar a câmara
        camera.update();

        // Atualizar o lote de desenho
        loteDesenho.setProjectionMatrix(camera.combined);

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
        // Dispose of textures
        jogadorTextura.dispose();
        inimigoTextura.dispose();
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
        for (Sound som : sons) {
            som.dispose();
            // If som is a Sound object, you would call som.dispose() if it were a Sound
            // If som is not a Sound object, you may need to handle it differently
            // Example: if som is a Sound object, you would call som.dispose()
            if (som instanceof Sound) {
                 ((Sound) som).dispose();
            }
            // If som is a Sound object, you would call som.dispose() if it were a Sound
            // Dispose of sounds if necessary
            if (som instanceof com.badlogic.gdx.audio.Sound) {
                ((com.badlogic.gdx.audio.Sound) som).dispose();
            } else if (som instanceof com.badlogic.gdx.audio.Music) {
                ((com.badlogic.gdx.audio.Music) som).dispose();
            }
            // If som is a Sound object, you would call som.dispose() if it were a Sound
            // If som is not a Sound object, you may need to handle it differently
            // Example: if som is a Sound object, you would call som.dispose()
            // if (som instanceof Sound) {
            //     ((Sound) som).dispose();
            // }
            // If som is a Sound object, you would call som.dispose() if it were a Sound
            // if (som instanceof com.badlogic.gdx.audio.Sound) {
            //     ((com.badlogic.gdx.audio.Sound) som).dispose();
            // } else if (som instanceof com.badlogic.gdx.audio.Music) {
            //     ((com.badlogic.gdx.audio.Music) som).dispose();
            // }

            // Assuming som is a Sound object, you would call som.dispose() if it were a Sound
            // som.dispose(); // Uncomment if som is a Sound object
            // If som is not a Sound object, you may need to handle it differently

        }


    }

}
