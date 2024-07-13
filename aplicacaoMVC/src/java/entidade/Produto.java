package entidade;

public class Produto {

    private int id;
    private String nomeProduto;
    private String descricao;
    private float precoCompra;
    private float precoVenda;
    private int quantidadeDisponivel;
    private String liberadoVenda;
    private Categoria categoria;

    public Produto() {}
    
public Produto(String nome, String descricao, float precoCompra, float precoVenda, int quantidadeDisponivel, String liberadoVenda, Categoria categoria) {
    this.nomeProduto = nome;
    this.descricao = descricao;
    this.precoCompra = precoCompra;
    this.precoVenda = precoVenda;
    this.quantidadeDisponivel = quantidadeDisponivel;
    this.liberadoVenda = liberadoVenda;
    this.categoria = categoria;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getLiberadoVenda() {
        return liberadoVenda;
    }

    public void setLiberadoVenda(String liberadoVenda) {
        this.liberadoVenda = liberadoVenda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void atualizaQuantidade(int quantidade){
        this.quantidadeDisponivel += quantidade;
    }
}
