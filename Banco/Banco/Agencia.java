package Banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Cliente {
    private int identificador;
    private String nomeCompleto;
    private String documento;
    private double saldo;

    public Cliente(int identificador, String nomeCompleto, String documento, double saldo) {
        this.identificador = identificador;
        this.nomeCompleto = nomeCompleto;
        this.documento = documento;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador +
               "\nNome Completo: " + nomeCompleto +
               "\nSaldo: R$" + saldo;
    }

     public boolean realizarTransferencia(Cliente destinatario, double valor) {
        if (valor > 0 && valor <= saldo && destinatario != null) {
            saldo -= valor;
            destinatario.saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean realizarSaque(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean realizarDeposito(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        } else {
            return false;
        }
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getSaldo() {
        return saldo;
    }
}

public class Agencia {
    private static List<Cliente> clientes = new ArrayList<>();
    private static int proximoIdentificador = 1;

    private static Cliente encontrarClientePorIdentificador(int identificador) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador() == identificador) {
                return cliente;
            }
        }
        return null;
    }

    private static void realizarTransferencia(Scanner scanner) {
        System.out.print("Identificador do cliente remetente: ");
        int idRemetente = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Identificador do cliente destinatário: ");
        int idDestinatario = scanner.nextInt();
        scanner.nextLine();
    
        System.out.print("Valor a ser transferido: R$");
        double valorTransferencia = scanner.nextDouble();
        scanner.nextLine();
    
        Cliente remetente = encontrarClientePorIdentificador(idRemetente);
        Cliente destinatario = encontrarClientePorIdentificador(idDestinatario);
    
        if (remetente != null && destinatario != null) {
            boolean transferenciaSucesso = remetente.realizarTransferencia(destinatario, valorTransferencia);
            if (transferenciaSucesso) {
                System.out.println("Transferência bem-sucedida.");
                System.out.println("Novo saldo do cliente remetente (ID " + idRemetente + "): R$" + remetente.getSaldo());
                System.out.println("Novo saldo do cliente destinatário (ID " + idDestinatario + "): R$" + destinatario.getSaldo());
            } else {
                System.out.println("Falha ao realizar a transferência. Saldo insuficiente, valor inválido ou cliente de destino nulo.");
            }
        } else {
            System.out.println("Cliente remetente ou destinatário não encontrado.");
        }
    }

    private static void realizarSaque(Scanner scanner) {
        System.out.print("Identificador do cliente para saque: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor a ser sacado: R$");
        double valorSaque = scanner.nextDouble();
        scanner.nextLine();

        Cliente cliente = encontrarClientePorIdentificador(idCliente);

        if (cliente != null) {
            boolean saqueSucesso = cliente.realizarSaque(valorSaque);
            if (saqueSucesso) {
                System.out.println("Saque bem-sucedido.");
                System.out.println("Novo saldo do cliente (ID " + idCliente + "): R$" + cliente.getSaldo());
            } else {
                System.out.println("Falha ao realizar o saque. Saldo insuficiente ou valor inválido.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void realizarDeposito(Scanner scanner) {
        System.out.print("Identificador do cliente para depósito: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor a ser depositado: R$");
        double valorDeposito = scanner.nextDouble();
        scanner.nextLine();

        Cliente cliente = encontrarClientePorIdentificador(idCliente);

        if (cliente != null) {
            boolean depositoSucesso = cliente.realizarDeposito(valorDeposito);
            if (depositoSucesso) {
                System.out.println("Depósito bem-sucedido.");
                System.out.println("Novo saldo do cliente (ID " + idCliente + "): R$" + cliente.getSaldo());
            } else {
                System.out.println("Falha ao realizar o depósito. Valor inválido.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastro de Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Realizar Transferência");
            System.out.println("4 - Realizar Saque");
            System.out.println("5 - Realizar Depósito");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    Cliente novoCliente = lerDadosClienteCadastro(scanner);
                    clientes.add(novoCliente);
                    System.out.println("Cliente cadastrado. Identificador: " + proximoIdentificador);
                    proximoIdentificador++;
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    realizarTransferencia(scanner);
                    break;
                case 4:
                    realizarSaque(scanner);
                    break;
                case 5:
                    realizarDeposito(scanner);
                    break;
                case 6:
                    System.out.println("Saindo do aplicativo.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Cliente lerDadosClienteCadastro(Scanner scanner) {
        System.out.print("Nome completo do cliente: ");
        String nomeCompleto = scanner.nextLine();

        System.out.print("Documento do cliente: ");
        String documento = scanner.nextLine();

        return new Cliente(proximoIdentificador, nomeCompleto, documento, 0.0);
    }

    private static void listarClientes() {
        System.out.println("\nLista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("\n" + cliente);
        }
    }
}