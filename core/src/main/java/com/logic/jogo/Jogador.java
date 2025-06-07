package com.logic.jogo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Jogador {

    // Jogador é um retângulo que representa o jogador no jogo

    public static Rectangle criarJogador() {
        Rectangle jogador = new Rectangle();
        jogador.x = 10;
        jogador.y = 10;
        jogador.width = 32;
        jogador.height = 32;

          return jogador;
    }
}
