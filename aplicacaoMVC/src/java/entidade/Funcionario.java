package entidade;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private Papel papel;
    private String senha;
    private String email;

    public Funcionario(String nome, String cpf, int papel, String senha, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.papel = Papel.values()[papel];
        this.senha = senha;
        this.email = email;
    }

    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.papel = Papel.VENDEDOR;
        this.senha = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
