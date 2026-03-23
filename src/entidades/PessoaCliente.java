package entidades;

public class PessoaCliente {
    Number idCliente;
    String cpf;
    String nome;
    String endereco;
    String email;

    public PessoaCliente(Number idCliente, String cpf, String nome, String endereco, String email) {
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    public Number getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Number idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void exibirCliente(){
        System.out.println("-------------------------");
        System.out.println("ID: " + this.getIdCliente());
        System.out.println("CPF: " + this.getCpf());
        System.out.println("NOME: " + this.getNome());
        System.out.println("ENDEREÇO: " + this.getEndereco());
        System.out.println("EMAIL: " + this.getEmail());

    }
}
