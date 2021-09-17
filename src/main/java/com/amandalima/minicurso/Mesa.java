package com.amandalima.minicurso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Mesa {

    private Integer numero;
    private int cadeiras;

    private List<Cliente> clientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public Mesa(int numero, int cadeiras) {

        if(cadeiras == 0){
            return;
        }

        this.numero = numero;
        this.cadeiras = cadeiras;
    }

    public Map<Integer, String> getOcupacao() {
        Map<Integer, String> info = new HashMap<>();
        IntStream.range(0, cadeiras).forEach(index -> {
            int numCadeira = index + 1;
            String ocupacao = "vazia";
            if(!clientes.isEmpty() && clientes.size() > index) {
                ocupacao = clientes.get(index).getNome();
            }
            info.put(numCadeira, ocupacao);
        });
        return info;
    }

    public int getNumero() {
        return numero;
    }
    public int getCadeiras() {
        return cadeiras;
    }
    public boolean lotada() {
        return this.cadeiras == clientes.size();
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void addPedido(Pedido pedido) {
        if (clientes.isEmpty()){
            return;
        }
        this.pedidos.add(pedido);
    }

    public Double totalConsumido() {
        return pedidos.stream().mapToDouble(Pedido::getValor).sum();
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }
}
