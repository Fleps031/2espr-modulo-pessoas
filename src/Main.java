import classes.ClienteDAOImpl;
import classes.ConexaoDBSingleton;
import classes.LojistaDAOImpl;
import entidades.PessoaCliente;
import entidades.PessoaLojista;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConexaoDBSingleton conexaoDb = ConexaoDBSingleton.getInstancia();
        Connection conn = conexaoDb.getConexao();

        ClienteDAOImpl cliente = new ClienteDAOImpl();
        LojistaDAOImpl lojista = new LojistaDAOImpl();
        cliente.clienteDAOImpl(conn);
        lojista.lojistaDAOImpl(conn);

        PessoaCliente novoCliente = new PessoaCliente(12, "12390854", "Felipe Molinari", "Rua das arvores 32", "superemail@email.com");

        cliente.listarClientes();
        cliente.salvarBanco(novoCliente);
        cliente.listarClientes();

        PessoaCliente clienteAtualizado = new PessoaCliente(12, "0000000000", "Nome Atualizado", "Rua das arvores 32", "superemail@email.com");


        cliente.atualizarDados(clienteAtualizado);
        cliente.buscarPorId("12");

        cliente.deletarConta(12);
        cliente.buscarPorId("12");

    }
}