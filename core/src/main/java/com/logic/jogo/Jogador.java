package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;

public class Jogador {
    public static Rectangle criarJogador() {
        Rectangle jogador = new Rectangle();
        jogador.x = 10;
        jogador.y = 10;
        jogador.width = 32;
        jogador.height = 32;
        return jogador;
    }
}



/*
package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Jogador {

   // jogador = new Rectangle();
    //jogador.x = 10;
    //jogador.y = 10;
    //jogador.width = 32;
    //jogador.height = 32;
     //   System.out.println(">> Jogador criados");

    // Inicializar jogadores
    private ArrayList<Rectangle> jogadores = new ArrayList<>();

    public Jogador() {
        for (int i = 0; i < 3; i++) {
            Rectangle jogador = new Rectangle();
            jogador.x = 50 + i * 100;
            jogador.y = 50 + i * 50;
            jogador.width = 32;
            jogador.height = 32;
            jogadores.add(jogador);
            System.out.println(">> Jogador adicionado");
        }
    }
}
*/
