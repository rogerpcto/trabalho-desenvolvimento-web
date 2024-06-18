package entidade;

public class Categoria {

    private int id;
    private String nomeCategoria;
  

    public Categoria(int id,String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeCategoria;
    }

    public void setNomeProduto(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    
}
