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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import com.badlogic.gdx.math.Rectangle;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    SpriteBatch loteDesenho;

    // Carregar texturas
    Texture jogadorTextura = new Texture("jogador.png");
    Texture inimigoTextura;
    Texture obstaculoTextura;
    Texture backgroundTexture;
    Texture portalTextura;
    // Declare camera and viewport
    OrthographicCamera camera;
    Viewport viewport;
    Rectangle portal;

    // Declare jogador, inimigo, obstáculo e outros objetos do jogo
    Rectangle jogador;
    Rectangle inimigo;

    // Array de obstáculos
    ArrayList<Rectangle> obstaculos;
    // Array de inimigos
    ArrayList<Rectangle> inimigos;
     // Array de texturas
    ArrayList<Texture> texturas;
    // Array de sons
    ArrayList<Sound> sons;

    // Declare som de colisão e música de fundo
    private Sound somColisao;
    private Music musicaFundo;

       // This is the constructor of the screen. You can initialize your screen here.
    @Override
    public void show() {

         // Inicializar lote de desenho
        loteDesenho = new SpriteBatch();

        // Criar câmara ortográfica
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        // Configurar a viewport
        viewport = new StretchViewport(800, 600, camera);// Definir a largura e altura da viewport
        viewport.apply();// Aplicar a viewport

        // Inicializar texturas
        // Carregar texturas
        jogadorTextura = new Texture("jogador.png");
        inimigoTextura = new Texture("inimigo.png");
        obstaculoTextura = new Texture("planeta64.png");
        backgroundTexture = new Texture("background.png");
        portalTextura = new Texture("portal.png"); // carregar textuta do portal

// Criar retângulo do portal
        portal = new Rectangle();
        portal.x = 580;
        portal.y = 350;
        portal.width = 48;
        portal.height = 48;

        // Inicializar jogador
        jogador = new Rectangle();// Criar um novo retângulo para o jogador
        jogador.x = 10;
        jogador.y = 10;
        jogador.width = 32;
        jogador.height = 32;
 //       jogador = Jogador.criarJogador();// Chamar o método estático para criar o jogador

        // Inicializar inimigo
        inimigo = new Rectangle();
        inimigo.x = 600;
        inimigo.y = 10;
        inimigo.width = 32;
        inimigo.height = 32;
        inimigos = Inimigo.criarInimigos();

        // Inicializar texturas
        texturas = new ArrayList<>();
        texturas.add(jogadorTextura);// Adicionar textura do jogador
        texturas.add(inimigoTextura);// Adicionar textura do inimigo
        texturas.add(obstaculoTextura);// Adicionar textura do obstáculo

        // Inicializar sons
        // Carregar sons
        musicaFundo = Gdx.audio.newMusic(Gdx.files.internal("musica_fundo.mp3")); // Substitua pelo nome do seu arquivo
        musicaFundo.setLooping(true); // Para tocar continuamente
        musicaFundo.setVolume(0.5f); // Volume entre 0.0 e 1.0
        musicaFundo.play();

        sons = new ArrayList<>();

        sons.add(Gdx.audio.newSound(Gdx.files.internal("laser.mp3")));

        somColisao = Gdx.audio.newSound(Gdx.files.internal("Colisao.mp3"));
        sons.add(somColisao);

        //Inicializar obstáculos
        // Criar obstáculos
        obstaculos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Rectangle obstaculo = new Rectangle();
            obstaculo.x = 50 + (i % 4) * 150;// Posição X do obstáculo
            obstaculo.y = 400 - (i / 4) * 150;// Posição Y do obstáculo
            obstaculo.width = 64; // Largura do obstáculo
            obstaculo.height = 64;// Altura do obstáculo
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
        tiros = new Array<>();

    }
// This method is called every frame to render the screen.

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
      viewport.apply();
        // Atualizar câmara

      camera.update();
      loteDesenho.setProjectionMatrix(camera.combined);

      loteDesenho.begin();
      //loteDesenho.draw(jogadorTextura, 32, 32);

      loteDesenho.end();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Tiro novoTiro = new Tiro(jogador.x + jogador.width / 2, jogador.y + jogador.height / 2);
            tiros.add(novoTiro);
        }

    }

    private void draw() {
// Desenhar o ecrã
         // Limpar o ecrã
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Limpar o ecrã
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
       // loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
        // Desenhar obstáculos
        for (Rectangle obstaculo : obstaculos) { // Verificar se "obstaculos" está inicializado e não é null
            loteDesenho.draw(obstaculoTextura, obstaculo.x, obstaculo.y, obstaculo.width, obstaculo.height);
        }

        // Desenhar inimigos
        for (Rectangle inimigo : inimigos) {
         loteDesenho.draw(inimigoTextura, inimigo.x, inimigo.y, inimigo.width, inimigo.height);
        }
// Desenhar o portal
        //for (int i = 0; i < 2; i++) {
            loteDesenho.draw(portalTextura, portal.x, portal.y, portal.width, portal.height);
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
        // Verificar se jogador entrou no portal
        if (jogador.overlaps(portal)) {
            musicaFundo.stop(); // parar música ao mudar de ecrã (opcional)
            ((Principal) Gdx.app.getApplicationListener()).setScreen(new SecondScreen((int) jogador.x, (int) jogador.y));
        }
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
        jogador.x = Math.max(0, Math.min(jogador.x, viewport.getWorldWidth() - jogador.width));
        jogador.y = Math.max(0, Math.min(jogador.y, viewport.getWorldHeight() - jogador.height));
    }

    // Verificar colisões com obstáculos
    // Aqui implementar a lógica de colisão entre o jogador e os obstáculos
    // se o jogador colidir com um obstáculo, pode empurrá-lo para trás ou impedir o movimentoized.

    private void checkCollisions() {
        boolean houveColisao = false;

        for (Rectangle obstaculo : obstaculos) {
            if (jogador.overlaps(obstaculo)) {
                // Parar a música de fundo
                if (musicaFundo.isPlaying()) {
                    musicaFundo.pause();
                }

                // Tocar som de colisão
                somColisao.play();

                // Resolver colisão (empurrar o jogador para trás)
                if (jogador.x < obstaculo.x) {
                    jogador.x = obstaculo.x - jogador.width;
                } else if (jogador.x > obstaculo.x + obstaculo.width) {
                    jogador.x = obstaculo.x + obstaculo.width;
                }

                if (jogador.y < obstaculo.y) {
                    jogador.y = obstaculo.y - jogador.height;
                } else if (jogador.y > obstaculo.y + obstaculo.height) {
                    jogador.y = obstaculo.y + obstaculo.height;
                }

                houveColisao = true;
            }
        }

        // Retomar a música de fundo se não houve colisão
        if (!houveColisao && !musicaFundo.isPlaying()) {
            musicaFundo.play();
        }
    }
    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
        System.out.println(">> Entrou no resize() com largura: " + width + " e altura: " + height);
        // Limpar o ecrã
        Gdx.gl.glClearColor(0, 0, 0, 1); // Cor de fundo preta
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Atualizar a viewport com a nova largura e altura
       // viewport.update(width, height, true);
        viewport = new StretchViewport(width, height, camera);
        // Aplicar a viewport
        viewport.apply();
        // Atualizar a câmara
        camera.setToOrtho(false, width, height);
        // Atualizar a câmara com a nova largura e altura
        camera.update();
        // viewport.update(width, height, true);
        // Atualizar a viewport
        //camera.setToOrtho(false, width, height);
        viewport.update(width, height);
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
        portalTextura.dispose();
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
            if (musicaFundo != null) musicaFundo.dispose();
            if (somColisao != null) somColisao.dispose();

        }

    }
    public class Tiro {
        public Rectangle bounds;
        public float velocidade = 300; // pixels por segundo

        public Tiro(float x, float y) {
            bounds = new Rectangle(x, y, 8, 8); // tamanho do tiro
        }

        public void update(float delta, Rectangle alvo) {
            // Calcular direção em linha reta para o inimigo
            float dx = alvo.x + alvo.width / 2 - (bounds.x + bounds.width / 2);
            float dy = alvo.y + alvo.height / 2 - (bounds.y + bounds.height / 2);
            float comprimento = (float) Math.sqrt(dx * dx + dy * dy);
            dx /= comprimento;
            dy /= comprimento;

            bounds.x += dx * velocidade * delta;
            bounds.y += dy * velocidade * delta;
        }

        public boolean colideCom(Rectangle alvo) {
            return bounds.overlaps(alvo);
        }
    }
    private Array<Tiro> tiros;

}
