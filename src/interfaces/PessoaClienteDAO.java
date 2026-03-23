package interfaces;

import entidades.PessoaCliente;

import java.sql.Connection;
import java.util.List;

public interface PessoaClienteDAO {
    void clienteDAOImpl(Connection conn);
    void salvarBanco(PessoaCliente  cliente);
    void atualizarDados(PessoaCliente cliente);
    void deletarConta(int idCliente);
    PessoaCliente buscarPorId(String id);
    List<PessoaCliente> listarClientes();
}
