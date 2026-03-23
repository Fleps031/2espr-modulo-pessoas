package entidades;

public class PessoaLojista {
    Number idLojista;
    Number idCliente;
    String cnpj;
    String nome;
    String endereco;
    String email;
    String tipoEmpresa;
    String categoriaEmpresa;

    public PessoaLojista(Number idLojista, Number idCliente, String cnpj, String nome, String endereco, String email, String tipoEmpresa, String categoriaEmpresa) {
        this.idLojista = idLojista;
        this.idCliente = idCliente;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.tipoEmpresa = tipoEmpresa;
        this.categoriaEmpresa = categoriaEmpresa;
    }


    public Number getIdLojista() {
        return idLojista;
    }

    public void setIdLojista(Number idLojista) {
        this.idLojista = idLojista;
    }

    public Number getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Number idCliente) {
        this.idCliente = idCliente;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getCategoriaEmpresa() {
        return categoriaEmpresa;
    }

    public void setCategoriaEmpresa(String categoriaEmpresa) {
        this.categoriaEmpresa = categoriaEmpresa;
    }

    public void exibirLojista(){
        System.out.println("-------------------------");
        System.out.println("ID LOJISTA: " + this.getIdLojista());
        System.out.println("ID CLIENTE: " + this.getIdLojista());
        System.out.println("CNPJ: " + this.getCnpj());
        System.out.println("NOME: " + this.getNome());
        System.out.println("CATEGORIA: " + this.getCategoriaEmpresa());
        System.out.println("TIPO DA EMPRESA: " + this.getTipoEmpresa());
        System.out.println("ENDEREÇO: " + this.getEndereco());
        System.out.println("EMAIL: " + this.getEmail());
    }
}
