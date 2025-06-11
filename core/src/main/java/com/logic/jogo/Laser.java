package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Laser {
    public Rectangle forma;
    public Vector3 direcao;
    public float velocidade = 400f;

    public Laser(float x, float y, Rectangle alvo) {
        forma = new Rectangle(x, y, 8, 4); // Tamanho do laser

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
