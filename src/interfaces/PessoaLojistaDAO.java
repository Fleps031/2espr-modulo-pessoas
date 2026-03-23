package interfaces;

import entidades.PessoaLojista;

import java.sql.Connection;
import java.util.List;

public interface PessoaLojistaDAO {
    void lojistaDAOImpl(Connection conn);
    void salvarBanco(PessoaLojista  lojista);
    void atualizarDados(PessoaLojista lojista);
    void deletarConta(int idLojista);
    PessoaLojista buscarPorId(String id);
    List<PessoaLojista> listarLojistas();
}