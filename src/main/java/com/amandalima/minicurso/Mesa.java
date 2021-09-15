package com.amandalima.minicurso;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private int numero;
    private int cadeiras;
    private List<Cliente> clientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public Mesa(int numero, int cadeiras) {
        this.numero = numero;
        this.cadeiras = cadeiras;
    }

    public int getCadeiras() {
        return this.cadeiras;
    }

    public int getNumero() {
        return numero;
    }

    public boolean lotada() {
        return this.cadeiras == clientes.size();
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Double totalConsumido() {
        return pedidos.stream().mapToDouble(Pedido::getValorTotal).sum();
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }
}
