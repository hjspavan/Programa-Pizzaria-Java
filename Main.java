import java.util.Scanner;

public class Main {

  // 📌 Lista de códigos dos clientes já cadastrados
  static int[] clientes = {1001, 1002, 1003};

  // 📌 Descontos correspondentes a cada cliente (mesmo índice do array clientes)
  static int[] descontos = {10, 50, 10};

  // 📌 Lista de pizzas disponíveis
  static String[] pizzas = {
          "Mussarela", "Calabresa", "Frango", "Quatro Queijos",
          "Portuguesa", "Bacon", "Napolitana", "Marguerita",
          "Pepperoni", "Palmito", "Atum", "Frango Catupiry",
          "Vegetariana", "Especial", "Cheddar"
  };

  // 📌 Preços das pizzas (mesmo índice do array pizzas)
  static double[] precos = {
          35, 40, 42, 45,
          48, 50, 38, 36,
          55, 37, 41, 47,
          39, 60, 44
  };

  // 📌 Vetor que guarda os pedidos (índices das pizzas escolhidas)
  static int[] pedido = new int[10];

  // 📌 Controla quantas pizzas já foram adicionadas no pedido
  static int quantidadePedido = 0;

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // 📌 Solicita código do cliente
    System.out.println("Digite seu código de cliente:");
    int codigo = sc.nextInt();

    // 📌 Busca se o cliente existe e pega o desconto dele
    int descontoCliente = buscarDesconto(codigo);

    // 📌 Se cliente não existir (-1), cadastra como novo
    if (descontoCliente == -1) {
      descontoCliente = 0; // sem desconto agora

      // adiciona cliente novo no sistema
      clientes = cadastrarCliente(codigo, clientes);

      // adiciona desconto padrão de 10% para próxima compra
      descontos = cadastrarDesconto(descontos, 10);

      System.out.println("Cliente novo cadastrado com 10% de desconto para próxima compra.");
    }

    int opcao;

    // 📌 Loop para escolher várias pizzas
    do {
      mostrarMenu(); // mostra cardápio

      System.out.println("Escolha uma pizza:");
      opcao = sc.nextInt();

      // 📌 Verifica se opção é válida
      if (opcao >= 0 && opcao < pizzas.length) {
        adicionarPedido(opcao); // adiciona pizza no pedido
      }

      System.out.println("Deseja mais uma pizza? (1 sim / 0 não)");

    } while (sc.nextInt() == 1);

    // 📌 Calcula valor total sem desconto
    double total = calcularTotal();

    // 📌 Aplica desconto do cliente
    double totalComDesconto = total * (1 - descontoCliente / 100.0);

    // 📌 Mostra resumo final
    System.out.println("\n--- RESUMO DO PEDIDO ---");

    mostrarPedido(); // mostra pizzas escolhidas

    System.out.println("Total sem desconto: R$ " + total);
    System.out.println("Desconto: " + descontoCliente + "%");
    System.out.println("Total final: R$ " + totalComDesconto);
  }

  // 🍕 MOSTRA O CARDÁPIO DE PIZZAS
  static void mostrarMenu() {
    System.out.println("\n--- CARDÁPIO ---");

    for (int i = 0; i < pizzas.length; i++) {
      System.out.println(i + " - " + pizzas[i] + " R$ " + precos[i]);
    }
  }

  // ➕ ADICIONA UMA PIZZA NO PEDIDO
  static void adicionarPedido(int opcao) {

    // salva o índice da pizza escolhida
    pedido[quantidadePedido] = opcao;

    // aumenta quantidade de itens no pedido
    quantidadePedido++;
  }

  // 🧾 MOSTRA O PEDIDO COMPLETO
  static void mostrarPedido() {

    // percorre todas as pizzas escolhidas
    for (int i = 0; i < quantidadePedido; i++) {

      int p = pedido[i]; // pega índice da pizza

      // mostra nome e preço da pizza
      System.out.println("- " + pizzas[p] + " R$ " + precos[p]);
    }
  }

  // 💰 CALCULA TOTAL DO PEDIDO
  static double calcularTotal() {

    double total = 0;

    for (int i = 0; i < quantidadePedido; i++) {

      int p = pedido[i];

      // soma preço de cada pizza
      total += precos[p];
    }

    return total;
  }

  // 🔍 PROCURA CLIENTE E RETORNA DESCONTO
  static int buscarDesconto(int codigo) {

    for (int i = 0; i < clientes.length; i++) {

      // se encontrar o cliente
      if (clientes[i] == codigo) {
        return descontos[i]; // retorna desconto dele
      }
    }

    return -1; // cliente não encontrado
  }

  // 🆕 CADASTRA NOVO CLIENTE
  static int[] cadastrarCliente(int codigo, int[] antigos) {

    // cria novo array maior
    int[] novo = new int[antigos.length + 1];

    // copia clientes antigos
    for (int i = 0; i < antigos.length; i++) {
      novo[i] = antigos[i];
    }

    // adiciona novo cliente no final
    novo[novo.length - 1] = codigo;

    return novo;
  }

  // 🆕 CADASTRA DESCONTO PARA NOVO CLIENTE
  static int[] cadastrarDesconto(int[] antigos, int desconto) {

    int[] novo = new int[antigos.length + 1];

    // copia descontos antigos
    for (int i = 0; i < antigos.length; i++) {
      novo[i] = antigos[i];
    }

    // adiciona novo desconto
    novo[novo.length - 1] = desconto;

    return novo;
  }
}