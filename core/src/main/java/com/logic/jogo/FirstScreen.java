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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    ArrayList<Laser> lasers = new ArrayList<>();
    Texture laserTextura = new Texture("laser2.png"); // Crie uma textura laser.png

    // Declare som de colisão e música de fundo
    private Sound somColisao;
    private Music musicaFundo;
    private float rotacaoPortal = 0f;

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
        obstaculoTextura = new Texture("planeta.png");
        backgroundTexture = new Texture("background.png");
        portalTextura = new Texture("portal.png"); // carregar textuta do portal

// Criar retângulo do portal
        portal = new Rectangle();
        portal.x = 580;
        portal.y = 350;
        portal.width = 48;
        portal.height = 48;

        // Inicializar jogador
        //jogador = new Rectangle();// Criar um novo retângulo para o jogador
        // jogador.x = 10;
        //  jogador.y = 10;
        // jogador.width = 32;
        // jogador.height = 32;
        jogador = Jogador.criarJogador();// Chamar o método estático para criar o jogador

        // Inicializar inimigo
        // inimigo = new Rectangle();// Criar um novo retângulo para o inimigo
        //inimigo.x = 600;
        // inimigo.y = 10;
        // inimigo.width = 32;
        //inimigo.height = 32;
        inimigos = Inimigo.criarInimigos();// Chamar o método estático para criar os inimigos

        // Gerar planetas aleatórios
        gerarPlanetasAleatorios();

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
        musicaFundo.play();// Iniciar a música de fundo
        somColisao = Gdx.audio.newSound(Gdx.files.internal("colisao.mp3"));// Som colisão
        sons = new ArrayList<>();// Inicializar a lista de sons
        // Adicionar sons à lista
        sons.add(Gdx.audio.newSound(Gdx.files.internal("explosao.mp3"))); // Substitua pelo nome do seu arquivo
        sons.add(Gdx.audio.newSound(Gdx.files.internal("laser.mp3")));//Som disparo
        sons.add(somColisao);// Adicionar som de colisão à lista


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
    private Rectangle encontrarInimigoMaisProximo() {
        Rectangle maisProximo = null;
        float menorDistancia = Float.MAX_VALUE;

        for (Rectangle inimigo : inimigos) {
            float distancia = Vector2.dst(jogador.x, jogador.y, inimigo.x, inimigo.y);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                maisProximo = inimigo;
            }
        }

        return maisProximo;
    }



    // Método para gerar planetas aleatórios
    private void gerarPlanetasAleatorios() {
        obstaculos = new ArrayList<>();

        // Definir constantes para o número de planetas e suas dimensões
        final int NUM_PLANETAS = 7; // ou outro número
        final int PLANETA_LARGURA = 48;
        final int PLANETA_ALTURA = 48;
        // nao sobrepor planetas sobre outros planetas ou sobre o jogador

        // Gerar planetas aleatórios
        for (int i = 0; i < NUM_PLANETAS; i++) {
            Rectangle obstaculo = new Rectangle();
            boolean sobrepoeJogador;

            do {
                float x = MathUtils.random(0, Gdx.graphics.getWidth() - PLANETA_LARGURA);
                float y = MathUtils.random(0, Gdx.graphics.getHeight() - PLANETA_ALTURA);

                obstaculo.set(x, y, PLANETA_LARGURA, PLANETA_ALTURA);

                // Verifica se o obstáculo está sobreposto ao jogador

                sobrepoeJogador = jogador.overlaps(obstaculo);
                // Verifica se o obstáculo está sobreposto a outros obstáculos
                for (Rectangle outroObstaculo : obstaculos) {
                    if (obstaculo.overlaps(outroObstaculo)) {
                        sobrepoeJogador = true; // Se sobrepõe a outro obstáculo, repete o loop
                        break; // Sai do loop se encontrar uma sobreposição
                    }
                }

            } while (sobrepoeJogador);

            obstaculos.add(obstaculo);
        }
        //metodo para gerar inimigos aleatórios
        inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = MathUtils.random(0, Gdx.graphics.getWidth() - 32);
            inimigo.y = MathUtils.random(0, Gdx.graphics.getHeight() - 32);
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
        }

        // Garantir que portal não está proximo dos obtaculos
        for (Rectangle obstaculo : obstaculos) {
            // Verificar se o portal está sobreposto a algum obstáculo
            if (portal.overlaps(obstaculo)) {
                // Reposicionar o portal para evitar sobreposição
                portal.x = MathUtils.random(0, Gdx.graphics.getWidth() - portal.width);
                portal.y = MathUtils.random(0, Gdx.graphics.getHeight() - portal.height);
            }
        }

        // Garantir que o portal não está proximo ao jogador
        if (portal.overlaps(jogador)) {
            // Reposicionar o portal para evitar sobreposição com o jogador
            portal.x = MathUtils.random(0, Gdx.graphics.getWidth() - portal.width);
            portal.y = MathUtils.random(0, Gdx.graphics.getHeight() - portal.height);
        }
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
    }

    private void draw() {
        // Desenhar o ecrã
        // Limpar o ecrã
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);

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
        // Desenhar o portal com rotação
        rotacaoPortal -= 90 * Gdx.graphics.getDeltaTime(); // 90 gr
        loteDesenho.draw(
            portalTextura,
            portal.x + portal.width / 2,  // centro x
            portal.y + portal.height / 2, // centro y
            portal.width / 2,             // origem da rotação x
            portal.height / 2,            // origem da rotação y
            portal.width,                 // largura
            portal.height,                // altura
            1f, 1f,                       // escala x, y
            rotacaoPortal,                // rotação
            0, 0,                         // recorte x, y
            portalTextura.getWidth(),
            portalTextura.getHeight(),
            false, false                  // flipX, flipY
        );
        for (Laser laser : lasers) {
            loteDesenho.draw(laserTextura, laser.forma.x, laser.forma.y, laser.forma.width, laser.forma.height);
        }
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
        checkCollisions();// Verificar colisões entre o jogador e os obstáculos
        // Verificar colisões entre o jogador e os inimigos
        for (Rectangle inimigo : inimigos) {
            if (jogador.overlaps(inimigo)) {
                // Parar a música de fundo se estiver a tocar
                if (musicaFundo.isPlaying()) {
                    musicaFundo.pause();
                    somColisao.play();
                }
                // Tocar som de colisão
                somColisao.play();
                // Reposicionar o jogador para evitar sobreposição
                //  jogador.x = Math.max(jogador.x, inimigo.x + inimigo.width); // Reposicionar o jogador para a direita do inimigo
            }
        }

        // Verificar se jogador entrou no portal
        if (jogador.overlaps(portal)) {
            //musicaFundo.stop(); // parar música ao mudar de ecrã (opcional)
            ((Principal) Gdx.app.getApplicationListener()).setScreen(new SecondScreen((int) jogador.x, (int) jogador.y));
        }

        /*
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);
            laser.atualizar(Gdx.graphics.getDeltaTime());

            // Remover laser se sair da tela
            if (laser.forma.x > viewport.getWorldWidth() || laser.forma.x < 0 ||
                laser.forma.y > viewport.getWorldHeight() || laser.forma.y < 0) {
                lasers.remove(i);
                i--;
            }
            */
        for (int i = 0; i < lasers.size(); i++) {
            Laser laser = lasers.get(i);

            for (int j = 0; j < inimigos.size(); j++) {
                if (laser.forma.overlaps(inimigos.get(j))) {
                    lasers.remove(i);
                    inimigos.remove(j);

                    sons.get(0).play(); // Explosão
                    // Aqui você pode também adicionar uma animação de explosão

                    i--;
                    break;
                }
            }
        }

    }


    private float tempoEntreTiros = 0.3f;
    private float tempoDesdeUltimoTiro = 0f;

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


        float lspeed = 200 * Gdx.graphics.getDeltaTime();
        tempoDesdeUltimoTiro += Gdx.graphics.getDeltaTime();

        // Movimentação
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) jogador.x -= lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) jogador.x += lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) jogador.y += lspeed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) jogador.y -= lspeed;

        // Converter posição do rato para coordenadas do jogo

        Vector3 lmousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(lmousePos);
        if (Gdx.input.isTouched()) {
            jogador.x -= lspeed;
            jogador.y -= lspeed;

        }
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.justTouched())
            && tempoDesdeUltimoTiro > tempoEntreTiros) {

            Rectangle alvo = encontrarInimigoMaisProximo();

            if (alvo != null) {
                Laser novoLaser = new Laser(
                    jogador.x + jogador.width / 2,
                    jogador.y + jogador.height / 2,
                    alvo
                );
                lasers.add(novoLaser);
                sons.get(1).play(); // Som de disparo
                tempoDesdeUltimoTiro = 0;
            }
        }
/*
        // Disparo com espaço ou clique
        if ((Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.justTouched())
            && tempoDesdeUltimoTiro > tempoEntreTiros) {
            Laser novoLaser = new Laser(jogador.x + jogador.width / 2, jogador.y + jogador.height / 2, lmousePos);
            lasers.add(novoLaser);
            sons.get(1).play(); // Som de disparo (laser)
            tempoDesdeUltimoTiro = 0;
        }
*/
        // Movimento com rato (se quiser)
        if (Gdx.input.isTouched()) {
            jogador.x += Math.signum(lmousePos.x - jogador.x) * speed;
            jogador.y += Math.signum(lmousePos.y - jogador.y) * speed;
        }

        jogador.x = Math.max(0, Math.min(jogador.x, viewport.getWorldWidth() - jogador.width));
        jogador.y = Math.max(0, Math.min(jogador.y, viewport.getWorldHeight() - jogador.height));

    }

    // Verificar colisões com obstáculos
    // Aqui implementar a lógica de colisão entre o jogador e os obstáculos

    private void checkCollisions() {
        // Verificar colisões entre o jogador e os obstáculos
        boolean houveColisao = false;
        for (Rectangle obstaculo : obstaculos) {
            if (jogador.overlaps(obstaculo)) {
                // Parar a música de fundo se estiver a tocar
                if (musicaFundo.isPlaying()) {
                    musicaFundo.play();
                }
                // Tocar som de colisão
                somColisao.play();

                // Calcular as diferenças entre os centros dos retângulos
                float jogadorCenterX = jogador.x + jogador.width / 2;
                float jogadorCenterY = jogador.y + jogador.height / 2;
                float obstaculoCenterX = obstaculo.x + obstaculo.width / 2;
                float obstaculoCenterY = obstaculo.y + obstaculo.height / 2;
                float diffX = jogadorCenterX - obstaculoCenterX;// diferença no eixo X
                float diffY = jogadorCenterY - obstaculoCenterY;// Diferença entre os centros dos retângulos
                float overlapX = (jogador.width / 2 + obstaculo.width / 2) - Math.abs(diffX);// sobreposição no eixo X
                float overlapY = (jogador.height / 2 + obstaculo.height / 2) - Math.abs(diffY);// sobreposição no eixo Y

                // Resolver a colisão no eixo com menos sobreposição
                if (overlapX < overlapY) {
                    // Corrigir no eixo X
                    if (diffX > 0) {
                        jogador.x += overlapX;// empurrar o jogador para a direita
                    } else {
                        jogador.x -= overlapX;// empurrar o jogador para a esquerda
                    }
                } else {
                    // Corrigir no eixo Y
                    if (diffY > 0) {
                        jogador.y += overlapY;// empurrar o jogador para cima
                    } else {
                        jogador.y -= overlapY;// empurrar o jogador para baixo
                    }
                }

                houveColisao = true;
            }
        }

        // Retomar a música se não houver colisão
        if (!houveColisao && !musicaFundo.isPlaying()) {
            musicaFundo.play();
        }
    }

    /*
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
     */
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
        // Dispose of sounds
        for (Sound som : sons) {
            som.dispose();
        }

        if (somColisao != null) {
            somColisao.stop(); // Stop the collision sound if it's playing
            somColisao.dispose(); // Dispose of the collision sound
        }
        // Dispose of the camera and viewport
        if (camera != null) camera = null; // Dispose of the camera
        if (viewport != null) viewport = null; // Dispose of the viewport

        // Dispose of the viewport
        if (viewport != null) {
            viewport.getCamera().position.set(0, 0, 0); // Reset camera position
            viewport.getCamera().update(); // Update the camera
            viewport = null; // Dispose of the viewport
        }

        // Dispose of the music and sound effects
        if (musicaFundo != null) musicaFundo.dispose();// Dispose of the music
        //if (somColisao != null) somColisao.dispose();// Dispose of the music

    }

}
