package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entidade.Compra;
import entidade.Fornecedor;
import entidade.Funcionario;
import entidade.Papel;
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
public class CompraDAO{

    public void Inserir(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO compra (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_comprador)"
                    + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setInt(1, compra.getQuantidade());
            sql.setDate(2, compra.getData());
            sql.setInt(3, compra.getValor());
            sql.setInt(4, compra.getFornecedor().getId());
            sql.setInt(5, compra.getProduto().getId());
            sql.setInt(6, compra.getComprador().getId());
            

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Compra getCompra(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, funcionarios.email as email_funcionario, fornecedores.email as email_fornecedor\n" +
                    "FROM compras \n" +
                    "JOIN fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
                    "JOIN produtos ON compras.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON compras.id_funcionario = funcionarios.id \n" +
                    "WHERE compras.ID = ?;");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Compra compra = new Compra();
            if (resultado != null) {
                while (resultado.next()) {
                    compra.setQuantidade(resultado.getInt("QUANTIDADE"));
                    compra.setData(resultado.getDate("DATA_COMPRA"));
                    compra.setValor(resultado.getInt("VALOR_COMPRA"));
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(resultado.getInt("ID_FORNECEDOR"));
                    fornecedor.setRazaoSocial(resultado.getString("RAZAO_SOCIAL"));
                    fornecedor.setCnpj(resultado.getString("CPNJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro ( resultado.getString("BAIRRO"));
                    fornecedor.setCidade( resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP")); 
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL_FORNECEDOR"));
                    compra.setFornecedor(fornecedor);
                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID_PRODUTO"));
                    // Faz depois o resto família
                    compra.setProduto(produto);
                    Funcionario comprador = new Funcionario();
                    comprador.setId(resultado.getInt("ID_FUNCIONARIO"));
                    comprador.setNome(resultado.getString("NOME"));
                    comprador.setCpf(resultado.getString("CPF"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    comprador.setPapel(Papel.values()[intPapel]);
                    comprador.setSenha(resultado.getString("SENHA"));
                    comprador.setEmail(resultado.getString("EMAIL_FUNCIONARIO"));
                    compra.setComprador(comprador);
                }
                
            }
            return compra;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
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
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
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
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Cliente> ListaDeClientes() {
        ArrayList<Cliente> meusClientes = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM funcionarios order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Cliente cliente = new Cliente();
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
            throw new RuntimeException("Query de select (ListaDeFuncionarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusClientes;
    }
  }
