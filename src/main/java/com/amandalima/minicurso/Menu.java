package com.amandalima.minicurso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class Menu {

    private static Scanner keyboard = new Scanner(System.in);
    private static List<Mesa> mesas = new ArrayList<>();

    public static void menuInicial() {

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

        System.out.print("Qual prato você deseja? ");
        String prato = keyboard.nextLine();
        System.out.print("Deseja alguma bebida? ");
        String bebida = keyboard.nextLine();
        System.out.print("Valor do pedido: ");
        double valorTotal = keyboard.nextDouble();

        mesa.addPedido(new Pedido(prato, bebida, valorTotal));
        voltarOuSair();
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
        System.out.print("Digite o nome do cliente ");
        String nome = keyboard.nextLine();
        mesa.addCliente(new Cliente(nome));
        voltarOuSair();
    }

    private static void verMesas() {
        if(mesas.isEmpty()) {
            System.out.println("Nenhuma mesa ocupada");
        }
        mesas.forEach(Menu::infoMesa);
        voltarOuSair();
    }

    private static void infoMesa(Mesa mesa) {
        out.println("Mesa " + mesa.getNumero() );
        IntStream.range(1, mesa.getCadeiras()).forEach(index -> {
            String status = (mesa.getClientes().isEmpty() || mesa.getClientes().size() < index) ? "vazia" : mesa.getClientes().get(index + 1).getNome();
            out.println("Cadeira " + index + " " + status);
        });
        out.println("Total consumido em R$: " + mesa.totalConsumido());
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

    private static void voltarOuSair() {
        System.out.println("Digite S para sair ou V para voltar");
        String opcao = keyboard.next().toUpperCase();

        switch (opcao) {
            case "S": {
                System.exit(0);
            }
            case "V": {
                menuInicial();
                break;
            }
            default: {
                System.out.println("Opção inválida.");
                voltarOuSair();
            }
        }

    }
}

