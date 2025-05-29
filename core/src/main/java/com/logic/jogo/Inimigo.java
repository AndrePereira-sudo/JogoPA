package com.logic.jogo;

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
}
