package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidade.Fornecedor;
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
public class FornecedorDAO{

    public void Inserir(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO clientes (razao_soical, cnpj, endereco, bairro, cidade, uf, cep, telefone, email)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Fornecedor getFornecedor(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM fornecedores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Fornecedor fornecedor = new Fornecedor();
                if (resultado != null) {
                while (resultado.next()) {
                    fornecedor.setRazaoSocial(resultado.getString("RAZAO_SOCIAL"));
                    fornecedor.setCnpj(resultado.getString("CPNJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro (resultado.getString("BAIRRO"));
                    fornecedor.setCidade(resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP")); 
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL"));
                }
                
            }
            return fornecedor;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select get (Fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE funcionarios SET razao_social = ?, cnpj = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE ID = ? ");
            sql.setString(1, fornecedor.getRazaoSocial());
            sql.setString(2, fornecedor.getCnpj());
            sql.setString(3, fornecedor.getEndereco());
            sql.setString(4, fornecedor.getBairro());
            sql.setString(5, fornecedor.getCidade());
            sql.setString(6, fornecedor.getUf());
            sql.setString(7, fornecedor.getCep());
            sql.setString(8, fornecedor.getTelefone());
            sql.setString(9, fornecedor.getEmail());
            sql.setInt(10, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update alterar (Fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Fornecedor fornecedor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, fornecedor.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Fornecedor> ListaDeFornecedores() {
        ArrayList<Fornecedor> meusFornecedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM fornecedores order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(resultado.getInt("ID"));
                    fornecedor.setRazaoSocial(resultado.getString("RAZAO_SOCIAL"));
                    fornecedor.setCnpj(resultado.getString("CPNJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro (resultado.getString("BAIRRO"));
                    fornecedor.setCidade(resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP")); 
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL"));
                    meusFornecedores.add(fornecedor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select ListaDeFornecedores (Fornecedor) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFornecedores;
    }
  }
