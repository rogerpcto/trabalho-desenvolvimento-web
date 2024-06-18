package entidade;

import java.time.LocalDate;

public class Venda {

    private int id;
    private int quantidade;
    private LocalDate data;
    private float valor;
    private Cliente cliente;
    private Produto produto;
    private Funcionario vendedor;

    public Venda( int quantidade, LocalDate data, float valor, Cliente cliente, Produto produto, Funcionario vendedor) {
        this.quantidade = quantidade;
        this.data = data;
        this.valor = valor;
        this.vendedor = vendedor;
        this.produto = produto;
        this.cliente = cliente;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }
}
