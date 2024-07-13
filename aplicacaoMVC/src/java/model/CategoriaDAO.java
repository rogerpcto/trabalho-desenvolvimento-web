package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Categoria;

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
public class CategoriaDAO{

    public void Inserir(Categoria categoria) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO categorias (nome_categoria)"
                    + " VALUES (?)");
            sql.setString(1, categoria.getNomeCategoria());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Categoria getCategoria(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT * FROM categorias\n" +
                    "WHERE categorias.ID = ?;");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Categoria categoria = new Categoria();
            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(resultado.getInt("ID"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                }
                
            }
            return categoria;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Categoria categoria) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE categorias SET nome_categoria = ? WHERE ID = ? ");
            sql.setString(1, categoria.getNomeCategoria());
            sql.setInt(2, categoria.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (Categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Categoria categoria) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM categorias WHERE ID = ? ");
            sql.setInt(1, categoria.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (Categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Categoria> ListaDeCategorias() {
        ArrayList<Categoria> minhasCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT *\n" +
                    "FROM categorias";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));

                    minhasCategorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select <ListaDeFuncionarios> (Categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasCategorias;
    }
  }
