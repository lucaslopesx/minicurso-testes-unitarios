package com.amandalima.minicurso;

public class Pedido {

    private  String prato;
    private  String bebida;
    private double valor = 0;

    public Pedido(String prato, String bebida, double valor) {
        this.prato = prato;
        this.bebida = bebida;
        this.valor = valor;
    }

    public String getPrato() {
        return prato;
    }

    public String getBebida() {
        return bebida;
    }

    public double getValor() {
        return valor;
    }
}
