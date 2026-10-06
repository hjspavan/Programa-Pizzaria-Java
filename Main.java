import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static int[] clientes = {1001, 1002, 1003};

    static int[] descontos = {10, 50, 10};

    static String[] pizzas = {
        "Mussarela", "Calabresa", "Frango", "Quatro Queijos",
        "Portuguesa", "Bacon", "Napolitana", "Marguerita",
        "Pepperoni", "Palmito", "Atum", "Frango Catupiry",
        "Vegetariana", "Especial", "Cheddar"
    };

    static double[] precos = {
        35, 40, 42, 45,
        48, 50, 38, 36,
        55, 37, 41, 47,
        39, 60, 44
    };

    static ArrayList<Integer> pedido = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite seu código de cliente:");
        int codigo = sc.nextInt();

        int descontoCliente = buscarDesconto(codigo);

        if (descontoCliente == -1) {
            descontoCliente = 0;

            clientes = cadastrarCliente(codigo, clientes);
            descontos = cadastrarDesconto(descontos, 10);

            System.out.println(
                "Cliente novo cadastrado com 10% de desconto para a próxima compra."
            );
        }

        int opcao;

        do {
            mostrarMenu();

            System.out.println("Escolha uma pizza:");
            opcao = sc.nextInt();

            if (opcao >= 0 && opcao < pizzas.length) {
                adicionarPedido(opcao);
            }

            System.out.println("Deseja mais uma pizza? (1 - Sim / 0 - Não)");

        } while (sc.nextInt() == 1);

        double total = calcularTotal();

        double totalComDesconto =
            total * (1 - descontoCliente / 100.0);

        System.out.println("\n--- RESUMO DO PEDIDO ---");

        mostrarPedido();

        System.out.println("Total sem desconto: R$ " + total);
        System.out.println("Desconto: " + descontoCliente + "%");
        System.out.println("Total final: R$ " + totalComDesconto);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("\n--- CARDÁPIO ---");

        for (int i = 0; i < pizzas.length; i++) {
            System.out.println(
                i + " - " + pizzas[i] + " R$ " + precos[i]
            );
        }
    }

    static void adicionarPedido(int opcao) {
        pedido.add(opcao);
    }

    static void mostrarPedido() {

        for (int p : pedido) {
            System.out.println(
                "- " + pizzas[p] + " R$ " + precos[p]
            );
        }
    }

    static double calcularTotal() {

        double total = 0;

        for (int p : pedido) {
            total += precos[p];
        }

        return total;
    }

    static int buscarDesconto(int codigo) {

        for (int i = 0; i < clientes.length; i++) {

            if (clientes[i] == codigo) {
                return descontos[i];
            }
        }

        return -1;
    }

    static int[] cadastrarCliente(int codigo, int[] antigos) {

        int[] novo = new int[antigos.length + 1];

        for (int i = 0; i < antigos.length; i++) {
            novo[i] = antigos[i];
        }

        novo[novo.length - 1] = codigo;

        return novo;
    }

    static int[] cadastrarDesconto(int[] antigos, int desconto) {

        int[] novo = new int[antigos.length + 1];

        for (int i = 0; i < antigos.length; i++) {
            novo[i] = antigos[i];
        }

        novo[novo.length - 1] = desconto;

        return novo;
    }
}
