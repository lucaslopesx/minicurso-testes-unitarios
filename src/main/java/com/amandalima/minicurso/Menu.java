package com.amandalima.minicurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

public class Menu {

    private static Scanner keyboard = new Scanner(System.in);
    private static List<Mesa> mesas = new ArrayList<>();

    public static void menuInicial() {

        out.println("____________________________________________________________________");
        System.out.println("Selecione uma opção");
        System.out.println("1 - Ver mesas");
        System.out.println("2 - Nova mesa");
        System.out.println("3 - Acomodar cliente");
        System.out.println("4 - Anotar pedido");
        System.out.println("0 - Sair");

        int opcao = keyboard.nextInt();

        switch (opcao) {
            case 1: verMesas();
            case 2: novaMesa();
            case 3: acomodarCliente();
            case 4: novoPedido();
            case 0: System.exit(0);
            default: {
                System.out.println("Opção inválida");
                menuInicial();
            }
        }

    }

    private static void novoPedido() {
        checaSeExisteMesa();
        Mesa mesa = buscaMesa();
        keyboard.nextLine();
        System.out.print("Qual prato você deseja? ");
        String prato = keyboard.nextLine();
        System.out.print("Deseja alguma bebida? ");
        String bebida = keyboard.nextLine();
        System.out.print("Valor do pedido: ");
        double valorTotal = keyboard.nextDouble();

        mesa.addPedido(new Pedido(prato, bebida, valorTotal));
        menuInicial();
    }

    private static void checaSeExisteMesa() {
        if(mesas.isEmpty()) {
            System.out.println("Nenhuma mesa disponível, monte uma nova mesa");
            novaMesa();
        }
    }

    private static void checaMesaDisponivel() {
        checaSeExisteMesa();
        if(mesas.stream().allMatch(Mesa::lotada)) {
            System.out.println("Todas as mesas estão lotadas, monte uma nova mesa");
            novaMesa();
        }
    }

    private static Mesa buscaMesa() {
        System.out.print("Digite o número da mesa ");
        int numeroMesa = keyboard.nextInt();

        Optional<Mesa> mesa = mesas.stream()
                .filter(item -> item.getNumero() == numeroMesa)
                .findFirst();

        if(mesa.isEmpty()) {
            System.out.println("Mesa não encontrada, tente novamente.");
            menuInicial();
        }
        return mesa.get();
    }

    private static void acomodarCliente() {
        checaMesaDisponivel();
        Mesa mesa = buscaMesa();
        keyboard.nextLine();
        System.out.print("Digite o nome do cliente ");
        String nome = keyboard.nextLine();
        mesa.addCliente(new Cliente(nome));
        menuInicial();
    }

    private static void verMesas() {
        if(mesas.isEmpty()) {
            System.out.println("Nenhuma mesa ocupada");
        }
        mesas.forEach(Menu::infoMesa);
        menuInicial();
    }

    private static void infoMesa(Mesa mesa) {
        out.println("Mesa " + mesa.getNumero() );
        Map<Integer, String> info = mesa.getOcupacao();
        info.forEach((numCadeira, ocupacao) -> out.println("Cadeira " + numCadeira + " " + ocupacao));
        mesa.getPedidos().forEach(pedido -> {
            out.println("Prato: " + pedido.getPrato() + " | Bebida: " + pedido.getBebida() + " | Valor: R$" + pedido.getValor());
        });
        out.println("Total consumido: R$" + mesa.totalConsumido());
    }

    private static void novaMesa() {

        out.print("Informe o número da nova mesa: ");
        int numero = keyboard.nextInt();
        out.print("Informe a quantidade de cadeiras: ");
        int cadeiras = keyboard.nextInt();

        if(mesas.stream().anyMatch(item -> item.getNumero() == numero)) {
            System.out.println("Já existe uma mesa " + numero);
            novaMesa();
        }

        Mesa mesa = new Mesa(numero, cadeiras);
        mesas.add(mesa);
        infoMesa(mesa);
        menuInicial();
    }
}

