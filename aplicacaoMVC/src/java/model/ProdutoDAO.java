package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidade.Categoria;
import entidade.Produto;
import java.util.ArrayList;

/*
-- Estrutura da tabela `funcionarios`

CREATE TABLE IF NOT EXISTS `funcionarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class ProdutoDAO{

    public void Inserir(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponível, liberado_venda, id_categoria)"
                    + " VALUES (?,?,?,?,?,?,?)");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setFloat(3, produto.getPrecoCompra());
            sql.setFloat(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, produto.getLiberadoVenda());
            sql.setInt(7, produto.getCategoria().getId());
            

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Produto getProduto(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, categorias.id as id_categoria \n" +
                    "FROM produtos \n" +
                    "JOIN categorias ON categorias.id = produtos.id_categoria \n" +
                    "WHERE produtos.id = ?;");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Produto produto = new Produto();
            if (resultado != null) {
                while (resultado.next()) {
                    
                    produto.setId(resultado.getInt("ID"));
                    produto.setNomeProduto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPrecoCompra(resultado.getFloat("PRECO_COMPRA"));
                    produto.setPrecoVenda(resultado.getFloat("PRECO_VENDA"));
                    produto.setQuantidadeDisponivel(resultado.getInt("QUANTIDADE_DISPONÍVEL"));
                    produto.setLiberadoVenda(resultado.getString("LIBERADO_VENDA"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID_CATEGORIA"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                    
                    produto.setCategoria(categoria);
                }
                
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE produtos SET nome_produto = ?, descricao = ?, preco_compra = ?, preco_venda = ?, quantidade_disponível = ?, liberado_venda = ?, id_categoria = ?  WHERE ID = ? ");
            sql.setString(1, produto.getNomeProduto());
            sql.setString(2, produto.getDescricao());
            sql.setFloat(3, produto.getPrecoCompra());
            sql.setFloat(4, produto.getPrecoVenda());
            sql.setInt(5, produto.getQuantidadeDisponivel());
            sql.setString(6, produto.getLiberadoVenda());
            sql.setInt(7, produto.getCategoria().getId());
            sql.setInt(8, produto.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (Produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Produto produto) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM produtos WHERE ID = ? ");
            sql.setInt(1, produto.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (Produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

     public ArrayList<Produto> ListaDeProdutos() {
        ArrayList<Produto> meusProdutos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            //acho que estamos mostrando as 2 tables, produtos e categorias, mas deviamos mostrar so as colunas de produtos?
            String selectSQL = "SELECT * FROM produtos JOIN categorias ON categorias.id = produtos.id_categoria order by nome_produto";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID"));
                    produto.setNomeProduto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPrecoCompra(resultado.getFloat("PRECO_COMPRA"));
                    produto.setPrecoVenda(resultado.getFloat("PRECO_VENDA"));
                    produto.setQuantidadeDisponivel(resultado.getInt("QUANTIDADE_DISPONÍVEL"));
                    produto.setLiberadoVenda(resultado.getString("LIBERADO_VENDA"));
                    
                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID_CATEGORIA"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                    
                    produto.setCategoria(categoria);
                    meusProdutos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeProdutos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusProdutos;
    }

    public Produto getPrimeiroProdutoCategoria(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, categorias.id as id_categoria \n" +
                    "FROM produtos \n" +
                    "JOIN categorias ON categorias.id = produtos.id_categoria \n" +
                    "WHERE id_categoria = ?;");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Produto produto = new Produto();
            if (resultado != null) {
                while (resultado.next()) {
                    produto.setId(resultado.getInt("ID"));
                    produto.setNomeProduto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPrecoCompra(resultado.getFloat("PRECO_COMPRA"));
                    produto.setPrecoVenda(resultado.getFloat("PRECO_VENDA"));
                    produto.setQuantidadeDisponivel(resultado.getInt("QUANTIDADE_DISPONÍVEL"));
                    produto.setLiberadoVenda(resultado.getString("LIBERADO_VENDA"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID_CATEGORIA"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                    
                    produto.setCategoria(categoria);
                }
                
            }
            return produto;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Produto) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}