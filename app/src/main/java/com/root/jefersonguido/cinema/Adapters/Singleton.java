package com.root.jefersonguido.cinema.Adapters;

import java.util.ArrayList;

/**
 * Created by Jeferson Eduardo on 01/11/2017.
 */

public class Singleton {
    private static Singleton instance = null;

    private Singleton(){

    }

    public static Singleton getIntance(){
        if (instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public Long getIdIngresso(int posicao) {
        return idingresso.get(posicao);
    }

    public void addIdIngresso(Long idingresso) {
        this.idingresso.add(idingresso);
    }

    public boolean removeIdIngresso(long posicao) {
        return idingresso.remove(posicao);
    }

    private ArrayList<Long> idingresso = new ArrayList<>();


}
