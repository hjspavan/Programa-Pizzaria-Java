import java.util.Scanner;

public class Main {

    // Lista de códigos dos clientes cadastrados
    static int[] clientes = {1001, 1002, 1003};

    // Descontos correspondentes a cada cliente
    static int[] descontos = {10, 50, 10};

    // Lista de pizzas disponíveis
    static String[] pizzas = {
        "Mussarela", "Calabresa", "Frango", "Quatro Queijos",
        "Portuguesa", "Bacon", "Napolitana", "Marguerita",
        "Pepperoni", "Palmito", "Atum", "Frango Catupiry",
        "Vegetariana", "Especial", "Cheddar"
    };

    // Preços correspondentes a cada pizza
    static double[] precos = {
        35, 40, 42, 45,
        48, 50, 38, 36,
        55, 37, 41, 47,
        39, 60, 44
    };

    // Armazena os índices das pizzas selecionadas no pedido
    static int[] pedido = new int[10];

    // Controla a quantidade de pizzas adicionadas ao pedido
    static int quantidadePedido = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Solicita o código do cliente
        System.out.println("Digite seu código de cliente:");
        int codigo = sc.nextInt();

        // Busca o desconto correspondente ao cliente
        int descontoCliente = buscarDesconto(codigo);

        // Caso o cliente não esteja cadastrado, realiza o cadastro
        if (descontoCliente == -1) {
            descontoCliente = 0;

            clientes = cadastrarCliente(codigo, clientes);
            descontos = cadastrarDesconto(descontos, 10);

            System.out.println(
                "Cliente novo cadastrado com 10% de desconto para a próxima compra."
            );
        }

        int opcao;

        // Permite selecionar várias pizzas
        do {
            mostrarMenu();

            System.out.println("Escolha uma pizza:");
            opcao = sc.nextInt();

            // Verifica se a opção selecionada é válida
            if (opcao >= 0 && opcao < pizzas.length) {
                adicionarPedido(opcao);
            }

            System.out.println("Deseja mais uma pizza? (1 - Sim / 0 - Não)");

        } while (sc.nextInt() == 1);

        // Calcula o valor total sem desconto
        double total = calcularTotal();

        // Aplica o desconto correspondente ao cliente
        double totalComDesconto =
            total * (1 - descontoCliente / 100.0);

        // Exibe o resumo do pedido
        System.out.println("\n--- RESUMO DO PEDIDO ---");

        mostrarPedido();

        System.out.println("Total sem desconto: R$ " + total);
        System.out.println("Desconto: " + descontoCliente + "%");
        System.out.println("Total final: R$ " + totalComDesconto);

        sc.close();
    }

    // Exibe o cardápio de pizzas
    static void mostrarMenu() {
        System.out.println("\n--- CARDÁPIO ---");

        for (int i = 0; i < pizzas.length; i++) {
            System.out.println(
                i + " - " + pizzas[i] + " R$ " + precos[i]
            );
        }
    }

    // Adiciona uma pizza ao pedido
    static void adicionarPedido(int opcao) {

        // Armazena o índice da pizza selecionada
        pedido[quantidadePedido] = opcao;

        // Atualiza a quantidade de pizzas no pedido
        quantidadePedido++;
    }

    // Exibe todas as pizzas selecionadas
    static void mostrarPedido() {

        for (int i = 0; i < quantidadePedido; i++) {

            int p = pedido[i];

            System.out.println(
                "- " + pizzas[p] + " R$ " + precos[p]
            );
        }
    }

    // Calcula o valor total do pedido
    static double calcularTotal() {

        double total = 0;

        for (int i = 0; i < quantidadePedido; i++) {

            int p = pedido[i];

            total += precos[p];
        }

        return total;
    }

    // Busca o desconto de um cliente pelo código
    static int buscarDesconto(int codigo) {

        for (int i = 0; i < clientes.length; i++) {

            if (clientes[i] == codigo) {
                return descontos[i];
            }
        }

        return -1;
    }

    // Cadastra um novo cliente
    static int[] cadastrarCliente(int codigo, int[] antigos) {

        // Cria um novo array com espaço adicional
        int[] novo = new int[antigos.length + 1];

        // Copia os clientes já cadastrados
        for (int i = 0; i < antigos.length; i++) {
            novo[i] = antigos[i];
        }

        // Adiciona o novo cliente
        novo[novo.length - 1] = codigo;

        return novo;
    }

    // Cadastra o desconto de um novo cliente
    static int[] cadastrarDesconto(int[] antigos, int desconto) {

        // Cria um novo array com espaço adicional
        int[] novo = new int[antigos.length + 1];

        // Copia os descontos existentes
        for (int i = 0; i < antigos.length; i++) {
            novo[i] = antigos[i];
        }

        // Adiciona o novo desconto
        novo[novo.length - 1] = desconto;

        return novo;
    }
}
