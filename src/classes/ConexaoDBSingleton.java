package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDBSingleton {
    private static ConexaoDBSingleton instancia;
    private Connection conexao;

    private ConexaoDBSingleton(){
        try{
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            String usuario = "RM559483";
            String senha = "100303";
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ConexaoDBSingleton getInstancia(){
        if(instancia == null){
            instancia = new ConexaoDBSingleton();
        }
        return instancia;
    }

    public Connection getConexao(){
        return conexao;
    }
}
