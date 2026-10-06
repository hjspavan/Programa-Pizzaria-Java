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

    static int[] pedido = new int[10];

    static int quantidadePedido = 0;

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
                "Cliente novo
