package com.root.jefersonguido.cinema.Adapters;

/**
 * Created by Jeferson Eduardo on 01/11/2017.
 */

public class Ingresso {

    private long _id;
    private String nome = "";
    private String telefone = "";
    private String data_nascimento = "";

    private String nomeFilme = "";
    private String sala = "";
    private String acento = "";
    private String valor = "";
    private String data_Hora_filme = "";

    public Ingresso(){

    }

    public Ingresso(long _id, String nome, String telefone, String data_nascimento, String nomeFilme, String data_Hora_filme, String sala, String acento, String valor) {
        this._id = _id;
        this.nome = nome;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.nomeFilme = nomeFilme;
        this.data_Hora_filme = data_Hora_filme;
        this.sala = sala;
        this.acento = acento;
        this.valor = valor;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getAcento() {
        return acento;
    }

    public void setAcento(String acento) {
        this.acento = acento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData_Hora_filme() {
        return data_Hora_filme;
    }

    public void setData_Hora_filme(String data_Hora_filme) {
        this.data_Hora_filme = data_Hora_filme;
    }

}
