package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Funcionario;
import entidade.Papel;

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
public class FuncionarioDAO{

    public void Inserir(Funcionario funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO funcionarios (nome, cpf, papel, senha, email)"
                    + " VALUES (?,?,?,?,?)");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setInt(3, funcionario.getPapel().ordinal());
            sql.setString(4, funcionario.getSenha());
            sql.setString(5, funcionario.getEmail());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Funcionario getFuncionario(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Funcionario funcionario = new Funcionario();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    funcionario.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionario.setNome(resultado.getString("NOME"));
                    funcionario.setCpf(resultado.getString("CPF"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    funcionario.setPapel(Papel.values()[intPapel]);
                    funcionario.setSenha(resultado.getString("SENHA"));
                }
            }
            return funcionario;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Funcionario funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE funcionarios SET nome = ?, cpf = ?, endereco = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, funcionario.getNome());
            sql.setString(2, funcionario.getCpf());
            sql.setInt(3, funcionario.getPapel().ordinal());
            sql.setString(4, funcionario.getSenha());
            sql.setInt(5, funcionario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Funcionario funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM funcionarios WHERE ID = ? ");
            sql.setInt(1, funcionario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Funcionario> ListaDeFuncionarios() {
        ArrayList<Funcionario> meusFuncionarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM funcionarios order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            Integer.parseInt(resultado.getString("PAPEL")),
                            resultado.getString("SENHA"),
                            resultado.getString("EMAIL"));
                    funcionario.setId(Integer.parseInt(resultado.getString("id")));
                    meusFuncionarios.add(funcionario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeFuncionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusFuncionarios;
    }

    public Funcionario Logar(Funcionario funcionario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM funcionarios WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, funcionario.getCpf());
            sql.setString(2, funcionario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Funcionario funcionarioObtido = new Funcionario();
            if (resultado != null) {
                while (resultado.next()) {
                    funcionarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    funcionarioObtido.setNome(resultado.getString("NOME"));
                    funcionarioObtido.setCpf(resultado.getString("CPF"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    funcionario.setPapel(Papel.values()[intPapel]);
                    funcionarioObtido.setSenha(resultado.getString("SENHA"));
                }
            }
            return funcionarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
   public ArrayList<Funcionario> ListaDeVendedores() {
        ArrayList<Funcionario> vendedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM funcionarios WHERE papel = 1";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            Integer.parseInt(resultado.getString("PAPEL")),
                            resultado.getString("SENHA"),
                            resultado.getString("EMAIL"));
                    funcionario.setId(Integer.parseInt(resultado.getString("id")));
                    vendedores.add(funcionario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeVendedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return vendedores;
    }
   
   public ArrayList<Funcionario> ListaDeCompradores() {
        ArrayList<Funcionario> vendedores = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM funcionarios WHERE papel = 2";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Funcionario funcionario = new Funcionario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            Integer.parseInt(resultado.getString("PAPEL")),
                            resultado.getString("SENHA"),
                            resultado.getString("EMAIL"));
                    funcionario.setId(Integer.parseInt(resultado.getString("id")));
                    vendedores.add(funcionario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeVendedores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return vendedores;
    }
}
