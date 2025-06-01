package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class Inimigo {
    public static ArrayList<Rectangle> criarInimigos() {
        ArrayList<Rectangle> inimigos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = 200 + i * 100;
            inimigo.y = 200 + i * 50;
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
            System.out.println(">> Inimigo " + i + " criado");
        }
        return inimigos;
    }
}




/*  package com.logic.jogo;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Inimigo {
    // Inicializar inimigos
    private ArrayList<Rectangle> inimigos = new ArrayList<>();

    public Inimigo() {
        for (int i = 0; i < 5; i++) {
            Rectangle inimigo = new Rectangle();
            inimigo.x = 200 + i * 100;
            inimigo.y = 200 + i * 50;
            inimigo.width = 32;
            inimigo.height = 32;
            inimigos.add(inimigo);
            System.out.println(">> adiciona inimigo criados");
        }
    }

/*
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
    */


