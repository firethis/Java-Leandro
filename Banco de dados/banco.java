import java.util.ArrayList;
import java.util.List;

class Cliente {
    private int identificador;
    private String nomeCompleto;
    private String documento;
    private double saldo;

    public Cliente(int identificador, String nomeCompleto, String documento) {
        this.identificador = identificador;
        this.nomeCompleto = nomeCompleto;
        this.documento = documento;
        this.saldo = 0.0; // Saldo inicial zerado
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso. Novo saldo: R$" + saldo);
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso. Novo saldo: R$" + saldo);
            return true;
        } else {
            System.out.println("Saldo insuficiente para realizar o saque de R$" + valor);
            return false;
        }
    }

    public boolean transferir(Cliente destinatario, double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            destinatario.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso para " + destinatario.getNomeCompleto());
            return true;
        } else {
            System.out.println("Saldo insuficiente para realizar a transferência de R$" + valor);
            return false;
        }
    }

    public static double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0.0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
}

public class banco{
    public static void main(String[] args) {
        // Criação de clientes
        Cliente cliente1 = new Cliente(1, "Fulano de Tal", "123456789");
        Cliente cliente2 = new Cliente(2, "Ciclano da Silva", "987654321");

        // Depositar e sacar
        cliente1.depositar(1000.0);
        cliente2.depositar(500.0);
        cliente1.sacar(200.0);

        // Transferência entre clientes
        cliente1.transferir(cliente2, 300.0);

        // Calcular saldo total
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);

        double saldoTotal = Cliente.calcularSaldoTotal(clientes);
        System.out.println("Saldo total de todos os clientes: R$" + saldoTotal);
    }
}
