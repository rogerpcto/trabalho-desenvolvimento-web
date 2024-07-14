package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidade.Cliente;
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
public class ClienteDAO{

    public void Inserir(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Cliente getCliente(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM clientes WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Cliente cliente = new Cliente();
            if (resultado != null) {
                while (resultado.next()) {
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setEndereco(resultado.getString("ENDERECO"));
                    cliente.setBairro ( resultado.getString("BAIRRO"));
                    cliente.setCidade( resultado.getString("CIDADE"));
                    cliente.setUf(resultado.getString("UF"));
                    cliente.setCep(resultado.getString("CEP")); 
                    cliente.setTelefone(resultado.getString("TELEFONE"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                    cliente.setId(resultado.getInt("ID"));
                }
                
            }
            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ?  WHERE ID = ? ");
            sql.setString(1, cliente.getNome());
            sql.setString(2, cliente.getCpf());
            sql.setString(3, cliente.getEndereco());
            sql.setString(4, cliente.getBairro());
            sql.setString(5, cliente.getCidade());
            sql.setString(6, cliente.getUf());
            sql.setString(7, cliente.getCep());
            sql.setString(8, cliente.getTelefone());
            sql.setString(9, cliente.getEmail());
            sql.setInt(10, cliente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (Cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Cliente cliente) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM clientes WHERE ID = ? ");
            sql.setInt(1, cliente.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (Cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Cliente> ListaDeClientes() {
        ArrayList<Cliente> meusClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM clientes order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(resultado.getInt("ID"));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setEndereco(resultado.getString("ENDERECO"));
                    cliente.setBairro ( resultado.getString("BAIRRO"));
                    cliente.setCidade( resultado.getString("CIDADE"));
                    cliente.setUf(resultado.getString("UF"));
                    cliente.setCep(resultado.getString("CEP")); 
                    cliente.setTelefone(resultado.getString("TELEFONE"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                    meusClientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select <ListaDeClientes> (Cliente) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusClientes;
    }
  }
