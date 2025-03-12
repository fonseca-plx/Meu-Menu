package application;

import entities.*;
import entities.enums.StatusPedido;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> ids = new ArrayList<>();
        List<Double> quantidades = new ArrayList<>();

        Menu menu = new Menu();
        Estoque estoque = new Estoque();
        ListaPedidos pedidos = new ListaPedidos();
        ListaClientes clientes = new ListaClientes();

        int opcao;
        char resposta;

        boolean iniciar = true;

        System.out.println("\nBem-vindo(a) ao MeuMenu!\n");
        System.out.println("Estabelecimento: Espetinho do Seu Zé\n");

        while (iniciar) {
            try {
                System.out.println("O que você gostaria de fazer?\n");
                System.out.println("1. Consultar o Menu do Restaurante\n2. Adicionar um novo prato ao Menu\n3. Excluir um prato do Menu\n4. Consultar Estoque de Ingredientes\n" +
                        "5. Adicionar ingredientes ao Estoque\n6. Fazer um pedido\n7. Cancelar um pedido\n8. Consultar pedidos\n9. Alterar status de um Pedido\n" +
                        "10. Cadastrar novo Cliente\n11. Consultar lista de Clientes\n12. Excluir Cliente\n13. Sair\n");
                System.out.print("Digite o número da tarefa: ");
                opcao = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (opcao) {
                    case 1:
                        System.out.println("Consultar o Menu do Restaurante\n");
                        menu.consultarMenu();
                        System.out.println("\n1. Voltar ao Menu Principal\n2. Encerrar\n");
                        System.out.print("Digite o número da tarefa: ");
                        opcao = sc.nextInt();
                        System.out.println();
                        if (opcao == 1) {
                            break;
                        }
                        if (opcao == 2) {
                            iniciar = false;
                        } else {
                            System.out.println("Opção inválida!\n");
                        }
                        break;
                    case 2:
                        System.out.println("Adicionar um novo prato ao Menu\n");
                        System.out.print("Os ingredientes que compõe este prato já estão cadastrados no Estoque? (S/N): ");
                        resposta = sc.next().toUpperCase().charAt(0);
                        sc.nextLine();
                        if (resposta == 'S') {
                            menu.adicionarPrato(criarPrato(sc, ids, quantidades, menu, estoque)); // add um novo prato a lista de pratos do Menu
                            System.out.println();
                        } else if (resposta == 'N') {
                            System.out.println("\nPrimeiramente vamos cadastrar os ingredientes no Estoque!\n");
                            System.out.print("De quantos ingredientes esse prato precisa? ");
                            int n = sc.nextInt();
                            sc.nextLine();
                            for (int i = 0; i < n; i++) {
                                System.out.println("\n" + (i + 1) + "º Ingrediente:");
                                Ingrediente ingrediente = criarIngrediente(sc, estoque);
                                System.out.print("Informe a quantidade desse ingrediente que será adicionada ao Estoque: ");
                                double quantidade = sc.nextDouble();
                                sc.nextLine();
                                System.out.println();
                                estoque.adicionarIngrediente(new IngredienteQuantidade(ingrediente, quantidade)); // add um novo ingrediente a lista de ingredientes do Estoque
                            }
                            System.out.println();
                            estoque.consultarEstoque();
                            System.out.println();
                            System.out.println("\nAgora podemos prosseguir com a adição do prato ao Menu!");
                            menu.adicionarPrato(criarPrato(sc, ids, quantidades, menu, estoque));
                            System.out.println();
                        } else {
                            System.out.println("\nComando inválido!");
                        }
                        System.out.println("\n1. Adicionar mais um prato ao Menu\n2. Voltar ao Menu Principal\n3. Encerrar\n");
                        System.out.print("Digite o número da tarefa: ");
                        opcao = sc.nextInt();
                        System.out.println();
                        if (opcao == 1) {
                            break;
                        }
                        if (opcao == 2) {
                            break;
                        }
                        if (opcao == 3) {
                            iniciar = false;
                        } else {
                            System.out.println("Opção inválida!\n");
                        }
                        break;
                    case 3:
                        System.out.println("Excluir um prato do Menu\n");
                        if (!menu.getPratos().isEmpty()) {
                            System.out.print("Informe o ID do prato que deseja excluir do Menu: ");
                            Prato prato = menu.buscarPratoPorId(sc.nextInt());
                            if (prato != null) {
                                System.out.printf("%nTem certeza que deseja excluir o prato %s do Menu? (S/N): ", prato.getNome());
                                resposta = sc.next().toUpperCase().charAt(0);
                                sc.nextLine();
                                if (resposta == 'S') {
                                    menu.excluirPrato(prato);
                                    System.out.println();
                                } else if (resposta == 'N') {
                                    System.out.println("\n1. Excluir outro prato do Menu\n2. Voltar ao Menu Principal\n3. Encerrar\n");
                                    System.out.print("Digite o número da tarefa: ");
                                    opcao = sc.nextInt();
                                    System.out.println();
                                    if (opcao == 1) {
                                        break;
                                    }
                                    if (opcao == 2) {
                                        break;
                                    }
                                    if (opcao == 3) {
                                        iniciar = false;
                                    } else {
                                        System.out.println("Opção inválida!\n");
                                    }
                                } else {
                                    System.out.println("Opção inválida!\n");
                                }
                            } else {
                                System.out.println("\nPrato não encontrado. Digite um IP válido.\n");
                            }
                        } else {
                            System.out.println("O menu está vazio.\n");
                        }
                        break;
                    case 4:
                        System.out.println("Consultar Estoque de Ingredientes\n");
                        estoque.consultarEstoque();
                        System.out.println("\n1. Voltar ao Menu Principal\n2. Encerrar\n");
                        System.out.print("Digite o número da tarefa: ");
                        opcao = sc.nextInt();
                        System.out.println();
                        if (opcao == 1) {
                            break;
                        }
                        if (opcao == 2) {
                            iniciar = false;
                        } else {
                            System.out.println("Opção inválida!\n");
                        }
                        break;
                    case 5:
                        System.out.println("Adicionar ingredientes ao Estoque\n");
                        System.out.print("Quantos ingredientes você deseja adicionar ao Estoque? ");
                        int n = sc.nextInt();
                        sc.nextLine();
                        for (int i = 0; i < n; i++) {
                            System.out.println("\n" + (i + 1) + "º Ingrediente:");
                            Ingrediente ingrediente = criarIngrediente(sc, estoque);
                            System.out.print("Informe a quantidade desse ingrediente que será adicionada ao Estoque: ");
                            double quantidade = sc.nextDouble();
                            sc.nextLine();
                            System.out.println();
                            estoque.adicionarIngrediente(new IngredienteQuantidade(ingrediente, quantidade)); // add um novo ingrediente a lista de ingredientes do Estoque
                        }
                        System.out.println();
                        estoque.consultarEstoque();
                        System.out.println();
                        System.out.println("\n1. Adicionar outro ingrediente ao Estoque\n2. Voltar ao Menu Principal\n3. Encerrar\n");
                        System.out.print("Digite o número da tarefa: ");
                        opcao = sc.nextInt();
                        System.out.println();
                        if (opcao == 1) {
                            break;
                        }
                        if (opcao == 2) {
                            break;
                        }
                        if (opcao == 3) {
                            iniciar = false;
                        } else {
                            System.out.println("Opção inválida!\n");
                        }
                        break;
                    case 6:
                        System.out.println("Fazer um pedido\n");
                        if (!menu.getPratos().isEmpty()) {
                            System.out.print("O pedido será realizado por um cliente cadastrado? (S/N): ");
                            resposta = sc.next().toUpperCase().charAt(0);
                            sc.nextLine();
                            if (resposta == 'S') {
                                System.out.println();
                                clientes.consultarClientes();
                                System.out.println();
                                System.out.print("Informe o ID do cliente: ");
                                int idCliente = sc.nextInt();
                                sc.nextLine();
                                pedidos.realizarPedido(criarPedido(pedidos, estoque, clientes, idCliente)); // criar pedido e adiciona-lo a lista de pedidos
                                int idPedido = pedidos.getPedidos().getLast().getId(); // id do último pedido adicionado a lista de pedidos
                                adicionarPrato(sc, menu, pedidos, idPedido);
                            } else if (resposta == 'N') {
                                System.out.print("\nO cliente deseja cadastrar-se antes de prosseguir com o pedido? (S/N): ");
                                resposta = sc.next().toUpperCase().charAt(0);
                                sc.nextLine();
                                if (resposta == 'S') {
                                    System.out.println("\nCadastro do Cliente:");
                                    Cliente cliente = criarCliente(sc, clientes);
                                    clientes.cadastrarCliente(cliente);
                                    pedidos.realizarPedido(criarPedido(pedidos, cliente, estoque));
                                    int idPedido = pedidos.getPedidos().getLast().getId();
                                    adicionarPrato(sc, menu, pedidos, idPedido);
                                } else if (resposta == 'N') {
                                    pedidos.realizarPedido(criarPedido(pedidos, estoque));
                                    int idPedido = pedidos.getPedidos().getLast().getId();
                                    adicionarPrato(sc, menu, pedidos, idPedido);
                                }
                            } else {
                                System.out.println("\nComando inválido!");
                            }
                        } else {
                            System.out.println("O menu está vazio.\n");
                        }
                        break;
                    case 7:
                        System.out.println("Cancelar pedido\n");
                        if (!pedidos.getPedidos().isEmpty()) {
                            System.out.print("Informe o ID do pedido que deseja cancelar: ");
                            int idPedido = sc.nextInt();
                            Pedido pedidoParaCancelar = pedidos.buscarPedidoPorId(idPedido);
                            if (pedidoParaCancelar != null) {
                                pedidoParaCancelar.alterarStatus(StatusPedido.CANCELADO);
                            } else {
                                System.out.println("\nPedido não encontrado!\n");
                            }
                        } else {
                            System.out.println("O registro de pedidos está vazio.\n");
                        }
                        break;
                    case 8:
                        System.out.println("Consultar pedidos\n");
                        pedidos.consultarPedidos();
                        System.out.println("\n1. Voltar ao Menu Principal\n2. Encerrar\n");
                        System.out.print("Digite o número da tarefa: ");
                        opcao = sc.nextInt();
                        System.out.println();
                        if (opcao == 1) {
                            break;
                        }
                        if (opcao == 2) {
                            iniciar = false;
                        } else {
                            System.out.println("Opção inválida!\n");
                        }
                        break;
                    case 9:
                        System.out.println("Alterar status do pedido\n");
                        if (!pedidos.getPedidos().isEmpty()) {
                            pedidos.consultarPedidos();
                            System.out.print("Informe o ID do pedido que deseja alterar o status: ");
                            int idPedido = sc.nextInt();
                            Pedido pedidoParaAlterarStatus = pedidos.buscarPedidoPorId(idPedido);
                            if (pedidoParaAlterarStatus != null) {
                                System.out.println("\nO status atual desse pedido é: " + pedidoParaAlterarStatus.getStatus());
                                System.out.println("\nQual o novo status do pedido?\n\n1. Pendente 2. Em preparação 3. Pronto 4. Entregue\n");
                                System.out.print("Digite o número do novo status: ");
                                opcao = sc.nextInt();
                                switch (opcao) {
                                    case 1:
                                        pedidoParaAlterarStatus.alterarStatus(StatusPedido.PENDENTE);
                                        break;
                                    case 2:
                                        pedidoParaAlterarStatus.alterarStatus(StatusPedido.EM_PREPARACAO);
                                        break;
                                    case 3:
                                        pedidoParaAlterarStatus.alterarStatus(StatusPedido.PRONTO);
                                        break;
                                    case 4:
                                        pedidoParaAlterarStatus.alterarStatus(StatusPedido.ENTREGUE);
                                        break;
                                    default:
                                        System.out.println("\nOpção inválida! Tente novamente!\n");
                                }
                            } else {
                                System.out.println("\nPedido não encontrado!\n");
                            }
                        } else {
                            System.out.println("O registro de pedidos está vazio.\n");
                        }
                        break;
                    case 10:
                        System.out.println("Cadastrar novo Cliente");
                        clientes.cadastrarCliente(criarCliente(sc, clientes));
                        System.out.println();
                        break;
                    case 11:
                        System.out.println("Consultar lista de Clientes\n");
                        clientes.consultarClientes();
                        System.out.println();
                        break;
                    case 12:
                        System.out.println("Excluir Cliente\n");
                        if (!clientes.getClientes().isEmpty()) {
                            clientes.consultarClientes();
                            System.out.println();
                            System.out.print("Informe o ID do cliente que deseja excluir da lista de clientes: ");
                            Cliente cliente = clientes.buscarClientePorId(sc.nextInt());
                            if (cliente != null) {
                                System.out.printf("%nTem certeza que deseja excluir o cliente %s? (S/N): ", cliente.getNome());
                                resposta = sc.next().toUpperCase().charAt(0);
                                sc.nextLine();
                                if (resposta == 'S') {
                                    clientes.excluirCliente(cliente);
                                    System.out.println();
                                } else if (resposta == 'N') {
                                    System.out.println("\n1. Voltar ao Menu Principal\n2. Encerrar\n");
                                    System.out.print("Digite o número da tarefa: ");
                                    opcao = sc.nextInt();
                                    System.out.println();
                                    if (opcao == 1) {
                                        break;
                                    }
                                    if (opcao == 2) {
                                        iniciar = false;
                                    } else {
                                        System.out.println("Opção inválida!\n");
                                    }
                                } else {
                                    System.out.println("Opção inválida!\n");
                                }
                            } else {
                                System.out.println("\nCliente não encontrado. Digite um IP válido.\n");
                            }
                        } else {
                            System.out.println("A lista de clientes está vazia.\n");
                        }
                        break;
                    case 13:
                        iniciar = false;
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente!\n");
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("\nErro: " + e.getMessage() + "\n");
                sc.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("\nEntrada inválida. Tente novamente!\n");
                sc.nextLine();
            }
        }

        System.out.println("Obrigado por usar o MeuMenu!\n");
        System.out.println("Encerrando o programa...");

        sc.close();
    }

    private static Prato criarPrato(Scanner sc, List<Integer> ids, List<Double> quantidades, Menu menu, Estoque estoque) {
        ids.clear(); // limpa a lista de números inteiros
        quantidades.clear(); // limpa a lista de números reais
        System.out.print("\nInforme o nome do prato: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.print("Informe o preço do prato: ");
        double preco = sc.nextDouble();
        sc.nextLine();
        System.out.print("Informe a descrição do prato: ");
        String descricao = sc.nextLine();
        System.out.print("\nInforme a quantidade de ingredientes que compõe este prato: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("\nInforme o ID do " + (i+1) + "º ingrediente: ");
            ids.add(sc.nextInt());
            System.out.print("Informe a quantidade deste ingrediente para o preparo do prato (apenas números): ");
            quantidades.add(sc.nextDouble());
        }
        return new Prato(menu.getPratos().size() + 1, nome, preco, descricao, estoque, ids, quantidades);
    }

    private static Ingrediente criarIngrediente(Scanner sc, Estoque estoque) {
        System.out.print("\nInforme o nome do ingrediente: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.print("Informe a unidade de medida desse ingrediente: ");
        String unidade = sc.nextLine();
        System.out.print("Informe o custo por unidade desse ingrediente (apenas números): ");
        double custo = sc.nextDouble();
        return new Ingrediente(estoque.getIngredientes().size() + 1, nome, unidade, custo);
    }

    private static Pedido criarPedido(ListaPedidos pedidos, Estoque estoque) {
        return new Pedido(pedidos.getPedidos().size() + 1, estoque);
    }

    private static Pedido criarPedido(ListaPedidos pedidos, Cliente cliente, Estoque estoque) {
        return new Pedido(pedidos.getPedidos().size() + 1, cliente, estoque);
    }

    private static Pedido criarPedido(ListaPedidos pedidos, Estoque estoque, ListaClientes clientes, int idCliente) {
        return new Pedido(pedidos.getPedidos().size() + 1, estoque, clientes, idCliente);
    }

    private static Cliente criarCliente(Scanner sc, ListaClientes clientes) {
        System.out.print("\nInforme o nome do cliente: ");
        String nome = sc.nextLine().toUpperCase();
        System.out.print("Informe seu número de telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Informe seu e-mail: ");
        String email = sc.nextLine();
        return new Cliente(clientes.getClientes().size() + 1, nome, telefone, email);
    }

    private static void adicionarPrato(Scanner sc, Menu menu, ListaPedidos pedidos, int idPedido) {
        System.out.println("\nAdicionar pratos ao pedido:\n");
        menu.consultarMenu();
        boolean addPrato = true;
        while (addPrato) {
            System.out.print("\nInforme o ID do prato que deseja adicionar ao pedido: ");
            int idPrato = sc.nextInt();
            Prato prato = menu.buscarPratoPorId(idPrato); // buscar prato no menu a partir do id fornecido
            if (prato != null) {
                System.out.print("Informe a quantidade desse prato no pedido: ");
                int quantidadePrato = sc.nextInt();
                pedidos.adicionarPratos(idPedido, prato, quantidadePrato); // add prato ao pedido
                System.out.println("1. Adicionar mais um prato ao pedido 2. Finalizar pedido");
                System.out.print("\nDigite o número da tarefa: ");
                int opcao = sc.nextInt();
                sc.nextLine();
                System.out.println();
                if (opcao == 2) {
                    addPrato = false;
                }
            }
            else {
                System.out.println("\nPrato não encontrado. Tente novamente!\n");
            }
        }
        System.out.println("\nPedido finalizado!\n");
        pedidos.getPedidos().getLast().consultar(); // mostrar detalhes do pedido
        System.out.println("\n");
    }
}
