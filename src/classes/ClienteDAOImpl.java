package classes;

import entidades.PessoaCliente;
import entidades.PessoaLojista;
import exceptions.PessoaNaoAtualizadaException;
import exceptions.PessoaNaoInseridaException;
import interfaces.PessoaClienteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements PessoaClienteDAO {
    Connection conn;

    @Override
    public void clienteDAOImpl(Connection conn) {
        this.conn = conn;
    };

    @Override
    public void salvarBanco(PessoaCliente pessoaCliente){
        String sql = "INSERT INTO CLIENTE (ID_CLIENTE, CPF, NOME, ENDERECO, EMAIL) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setString(1, pessoaCliente.getIdCliente().toString());
            preparedStatement.setString(2, pessoaCliente.getCpf());
            preparedStatement.setString(3, pessoaCliente.getNome());
            preparedStatement.setString(4, pessoaCliente.getEndereco());
            preparedStatement.setString(5, pessoaCliente.getEmail());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new PessoaNaoInseridaException("Erro ao salvar cliente: nenhuma linha foi inserida.");
            }

            System.out.println("Cliente salvo com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao salvar cliente no banco.", e);
        }
    }

    @Override
    public void atualizarDados(PessoaCliente cliente) {
        String sql = "UPDATE CLIENTE SET CPF = ?, NOME = ?, ENDERECO = ?, EMAIL = ? WHERE ID_CLIENTE = ?";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setString(1, cliente.getCpf());
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setString(3, cliente.getEndereco());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getIdCliente().toString());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new PessoaNaoAtualizadaException("Cliente não encontrado para atualização. ID: " + cliente.getIdCliente());
            }

            System.out.println("Cliente atualizado com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    @Override
    public void deletarConta(int idCliente) {
        String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setInt(1, idCliente);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if(linhasAfetadas == 0){
                throw new RuntimeException("Cliente não encontrado para exclusão. ID: " + idCliente);
            }

            System.out.println("Cliente deletado com sucesso!");

        } catch(SQLException e){
            throw new RuntimeException("Erro ao deletar cliente.", e);
        }
    }

    @Override
    public List<PessoaCliente> listarClientes() {
        System.out.println("Conexao cliente: " + this.conn);

        String sql = "SELECT * FROM CLIENTE";
        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PessoaCliente> listaClientes = new ArrayList<>();

            System.out.println("Buscando os clientes...");
            System.out.println("");
            while(resultSet.next()){
                PessoaCliente novoCliente = new PessoaCliente(
                        resultSet.getInt("ID_CLIENTE"),
                        resultSet.getString("CPF"),
                        resultSet.getString("NOME"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("EMAIL")
                );

                novoCliente.exibirCliente();

                listaClientes.add(novoCliente);
            }
            return listaClientes;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public PessoaCliente buscarPorId(String id) {

        String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";

        try(PreparedStatement preparedStatement = this.conn.prepareStatement(sql)){
            preparedStatement.setInt(1, Integer.parseInt(id));

            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                throw new RuntimeException("Cliente não encontrado com ID: " + id);
            }

            PessoaCliente novoCliente = new PessoaCliente(
                    resultSet.getInt("ID_CLIENTE"),
                    resultSet.getString("CPF"),
                    resultSet.getString("NOME"),
                    resultSet.getString("ENDERECO"),
                    resultSet.getString("EMAIL")
            );

            novoCliente.exibirCliente();
            return novoCliente;

        } catch(SQLException e){
            throw new RuntimeException("Erro ao buscar cliente por ID.", e);
        }
    }
}
