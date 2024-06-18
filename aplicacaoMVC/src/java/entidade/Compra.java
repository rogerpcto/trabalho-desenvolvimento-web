package entidade;

import java.sql.Date;



public class Compra {

    private int id;
    private int quantidade;
    private Date data;
    private int valor;
    private Fornecedor fornecedor;
    private Produto produto;
    private Funcionario comprador;

    
    public Compra(){}
    
    public Compra( int quantidade, Date data, int valor, Fornecedor fornecedor, Produto produto, Funcionario comprador) {
        this.quantidade = quantidade;
        this.data = data;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.comprador = comprador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getComprador() {
        return comprador;
    }

    public void setComprador(Funcionario comprador) {
        this.comprador = comprador;
    }
}
