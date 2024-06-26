package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidade.Categoria;
import entidade.Cliente;
import entidade.Compra;
import entidade.Fornecedor;
import entidade.Funcionario;
import entidade.Venda;
import entidade.Produto;
import entidade.Papel;
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
public class VendaDAO{

    public void Inserir(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO compras (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario)"
                    + " VALUES (?,?,?,?,?,?)");
            sql.setInt(1, venda.getQuantidade());
            sql.setDate(2, venda.getData());
            sql.setFloat(3, venda.getValor());
            sql.setInt(4, venda.getCliente().getId());
            sql.setInt(5, venda.getProduto().getId());
            sql.setInt(6, venda.getVendedor().getId());
            

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Venda getVenda(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, funcionarios.nome as nome_funcionario, funcionarios.email as email_funcionario\n" +
                    "FROM vendas \n" +
                    "JOIN clientes ON vendas.id_cliente = clientes.id\n" +
                    "JOIN produtos ON vendas.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON vendas.id_funcionario = funcionarios.id \n" +
                    "WHERE vendas.ID = ?;");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Venda venda = new Venda();
            if (resultado != null) {
                while (resultado.next()) {
                    venda.setQuantidade(resultado.getInt("QUANTIDADE_VENDA"));
                    venda.setData(resultado.getDate("DATA_VENDA"));
                    venda.setValor(resultado.getInt("VALOR_VENDA"));

                    Cliente cliente = new Cliente();
                    cliente.setId(resultado.getInt("ID_CLIENTE"));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setEndereco(resultado.getString("ENDERECO"));
                    cliente.setBairro ( resultado.getString("BAIRRO"));
                    cliente.setCidade( resultado.getString("CIDADE"));
                    cliente.setUf(resultado.getString("UF"));
                    cliente.setCep(resultado.getString("CEP")); 
                    cliente.setTelefone(resultado.getString("TELEFONE"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                    
                    venda.setCliente(cliente);

                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID_PRODUTO"));
                    produto.setNomeProduto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPrecoCompra(resultado.getFloat("PRECO_COMPRA"));
                    produto.setPrecoVenda(resultado.getFloat("PRECO_VENDA"));
                    produto.setQuantidadeDisponivel(resultado.getInt("QUANTIDADE_DISPONIVEL"));
                    produto.setLiberadoVenda(resultado.getString("LIBERADO_VENDA"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID_CATEGORIA"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                    
                    produto.setCategoria(categoria);
                    venda.setProduto(produto);

                    Funcionario vendedor = new Funcionario();
                    vendedor.setId(resultado.getInt("ID_FUNCIONARIO"));
                    vendedor.setNome(resultado.getString("NOME_FUNCIONARIO"));
                    vendedor.setCpf(resultado.getString("CPF"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    vendedor.setPapel(Papel.values()[intPapel]);
                    vendedor.setSenha(resultado.getString("SENHA"));
                    vendedor.setEmail(resultado.getString("EMAIL_FUNCIONARIO"));
                    
                    venda.setVendedor(vendedor);
                }
                
            }
            return venda;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Venda) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE vendas SET quantidade_venda = ?, data_venda = ?, valor_venda = ?, id_cliente = ?, id_produto = ?, id_funcionario WHERE ID = ? ");
            sql.setInt(1, venda.getQuantidade());
            sql.setDate(2, venda.getData());
            sql.setFloat(3, venda.getValor());
            sql.setInt(4, venda.getCliente().getId());
            sql.setInt(5, venda.getProduto().getId());
            sql.setInt(6, venda.getVendedor().getId());
            sql.setInt(7, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Venda venda) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM vendas WHERE ID = ? ");
            sql.setInt(1, venda.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Venda> ListaDeVendas() {
        ArrayList<Venda> minhasVendas = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT *, funcionarios.nome as nome_funcionario, funcionarios.email as email_funcionario\n" +
                    "FROM vendas \n" +
                    "JOIN cliente ON vendas.id_cliente = cliente.id\n" +
                    "JOIN produtos ON vendas.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON vendas.id_funcionario = funcionarios.id";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Venda venda = new Venda();
                    venda.setQuantidade(resultado.getInt("QUANTIDADE_VENDA"));
                    venda.setData(resultado.getDate("DATA_VENDA"));
                    venda.setValor(resultado.getInt("VALOR_VENDA"));

                    Cliente cliente = new Cliente();
                    cliente.setId(resultado.getInt("ID_CLIENTE"));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setEndereco(resultado.getString("ENDERECO"));
                    cliente.setBairro ( resultado.getString("BAIRRO"));
                    cliente.setCidade( resultado.getString("CIDADE"));
                    cliente.setUf(resultado.getString("UF"));
                    cliente.setCep(resultado.getString("CEP")); 
                    cliente.setTelefone(resultado.getString("TELEFONE"));
                    cliente.setEmail(resultado.getString("EMAIL"));
                    
                    venda.setCliente(cliente);

                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID_PRODUTO"));
                    produto.setNomeProduto(resultado.getString("NOME_PRODUTO"));
                    produto.setDescricao(resultado.getString("DESCRICAO"));
                    produto.setPrecoCompra(resultado.getFloat("PRECO_COMPRA"));
                    produto.setPrecoVenda(resultado.getFloat("PRECO_VENDA"));
                    produto.setQuantidadeDisponivel(resultado.getInt("QUANTIDADE_DISPONIVEL"));
                    produto.setLiberadoVenda(resultado.getString("LIBERADO_VENDA"));

                    Categoria categoria = new Categoria();
                    categoria.setId(resultado.getInt("ID_CATEGORIA"));
                    categoria.setNomeCategoria(resultado.getString("NOME_CATEGORIA"));
                    
                    produto.setCategoria(categoria);
                    venda.setProduto(produto);

                    Funcionario vendedor = new Funcionario();
                    vendedor.setId(resultado.getInt("ID_FUNCIONARIO"));
                    vendedor.setNome(resultado.getString("NOME_FUNCIONARIO"));
                    vendedor.setCpf(resultado.getString("CPF"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    vendedor.setPapel(Papel.values()[intPapel]);
                    vendedor.setSenha(resultado.getString("SENHA"));
                    vendedor.setEmail(resultado.getString("EMAIL_FUNCIONARIO"));
                    
                    venda.setVendedor(vendedor);
                    minhasVendas.add(venda);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select <ListaDeFuncionarios> (Vendas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasVendas;
    }
  }
