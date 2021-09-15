package com.amandalima.minicurso;

public class Pedido {

    private  String prato;
    private  String bebida;
    private double valorTotal = 0;

    public Pedido(String prato, String bebida, double valor) {
        this.prato = prato;
        this.bebida = bebida;
        this.valorTotal = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
