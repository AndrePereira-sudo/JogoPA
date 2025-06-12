package com.logic.jogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/*public class Laser {

        public Rectangle forma;
        public Vector3 direcao;
        public float velocidade = 400f;

        public Laser(float x, float y, Vector3 alvo) {
            forma = new Rectangle(x, y, 8, 4); // Tamanho do laser

            // Calcular direção normalizada
            direcao = new Vector3(alvo.x - x, alvo.y - y, 0).nor();
        }

        public void atualizar(float delta) {
            forma.x += direcao.x * velocidade * delta;
            forma.y += direcao.y * velocidade * delta;
        }
    }

*/
/*
public class Laser {
    public Rectangle forma;
    public Vector3 direcao;
    public float velocidade = 400f;

    public Laser(float x, float y, Rectangle alvo) {
        forma = new Rectangle(x, y, 4, 4); // Tamanho do laser

        // Centro do inimigo
        float alvoX = alvo.x + alvo.x / 2;
        float alvoY = alvo.y + alvo.y / 2;

        // Direção até o alvo (normalizada)
        direcao = new Vector3(alvoX - x, alvoY - y, 0).nor();
    }

    public void atualizar(float delta) {
        forma.x += direcao.x * velocidade * delta;
        forma.y += direcao.y * velocidade * delta;
    }
}

 */
public class Laser {
    private Vector2 position;
    private Vector2 direction;
    private float speed = 400f;
    private Texture texture;

    public Laser(float x, float y, Vector2 target) {
        this.position = new Vector2(x, y);
        this.texture = new Texture("laser.png");

        // Calcula a direção normalizada em direção ao alvo
        this.direction = new Vector2(target).sub(position).nor();
    }

    public void update(float delta) {
        position.mulAdd(direction, speed * delta);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public Vector2 getPosition() {
        return position;
    }
}
