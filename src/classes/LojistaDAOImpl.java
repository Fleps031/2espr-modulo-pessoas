package classes;

import entidades.PessoaCliente;
import entidades.PessoaLojista;
import exceptions.PessoaNaoAtualizadaException;
import exceptions.PessoaNaoDeletadaException;
import exceptions.PessoaNaoInseridaException;
import interfaces.PessoaLojistaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LojistaDAOImpl implements PessoaLojistaDAO {
    Connection conn;

    @Override
    public void lojistaDAOImpl(Connection conn) {
        this.conn = conn;
    };

    @Override
    public void salvarBanco(PessoaLojista lojista){
        String sql = "INSERT INTO LOJISTA (ID_LOJISTA, ID_CLIENTE, CNPJ, NOME, ENDERECO, EMAIL, TIPO_EMPRESA, CATEGORIA_EMPRESA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setString(1, lojista.getIdLojista().toString());
            preparedStatement.setString(2, lojista.getIdCliente().toString());
            preparedStatement.setString(3, lojista.getCnpj());
            preparedStatement.setString(4, lojista.getNome());
            preparedStatement.setString(5, lojista.getEndereco());
            preparedStatement.setString(6, lojista.getEmail());
            preparedStatement.setString(7, lojista.getTipoEmpresa());
            preparedStatement.setString(8, lojista.getCategoriaEmpresa());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new PessoaNaoInseridaException("Erro ao salvar lojista: nenhuma linha foi inserida.");
            }

            System.out.println("Lojista salvo com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao salvar lojista no banco.", e);
        }
    }

    @Override
    public void atualizarDados(PessoaLojista lojista) {
        String sql = "UPDATE LOJISTA SET CNPJ = ?, NOME = ?, ENDERECO = ?, EMAIL = ?, TIPO_EMPRESA = ?, CATEGORIA_EMPRESA = ? WHERE ID_LOJISTA = ?";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setString(1, lojista.getCnpj());
            preparedStatement.setString(2, lojista.getNome());
            preparedStatement.setString(3, lojista.getEndereco());
            preparedStatement.setString(4, lojista.getEmail());
            preparedStatement.setString(5, lojista.getTipoEmpresa());
            preparedStatement.setString(6, lojista.getCategoriaEmpresa());
            preparedStatement.setInt(7, lojista.getIdLojista().intValue());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new PessoaNaoAtualizadaException("Lojista não encontrado para atualização. ID: " + lojista.getIdLojista());
            }

            System.out.println("Lojista atualizado com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar lojista.", e);
        }
    }

    @Override
    public void deletarConta(int idLojista) {
        String sql = "DELETE FROM LOJISTA WHERE ID_LOJISTA = ?";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setInt(1, idLojista);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new PessoaNaoDeletadaException("Lojista não encontrado para exclusão. ID: " + idLojista);
            }

            System.out.println("Lojista deletado com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar lojista.", e);
        }
    }

    @Override
    public List<PessoaLojista> listarLojistas() {
        String sql = "SELECT * FROM LOJISTA";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PessoaLojista> lista = new ArrayList<>();

            while(resultSet.next()){
                PessoaLojista lojista = new PessoaLojista(
                        resultSet.getInt("ID_LOJISTA"),
                        resultSet.getInt("ID_CLIENTE"),
                        resultSet.getString("CNPJ"),
                        resultSet.getString("NOME"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("TIPO_EMPRESA"),
                        resultSet.getString("CATEGORIA_EMPRESA")
                );

                lista.add(lojista);

                lojista.exibirLojista();
            }

            return lista;

        } catch(SQLException e){
            throw new RuntimeException("Erro ao listar lojistas.", e);
        }
    }

    @Override
    public PessoaLojista buscarPorId(String id) {
        String sql = "SELECT * FROM LOJISTA WHERE ID_LOJISTA = ?";

        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, Integer.parseInt(id));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new RuntimeException("Lojista não encontrado com ID: " + id);
            }

            PessoaLojista lojista = new PessoaLojista(
                    resultSet.getInt("ID_LOJISTA"),
                    resultSet.getInt("ID_CLIENTE"),
                    resultSet.getString("CNPJ"),
                    resultSet.getString("NOME"),
                    resultSet.getString("ENDERECO"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("TIPO_EMPRESA"),
                    resultSet.getString("CATEGORIA_EMPRESA")
            );

            return lojista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar lojista por ID.", e);
        }
    }
}
