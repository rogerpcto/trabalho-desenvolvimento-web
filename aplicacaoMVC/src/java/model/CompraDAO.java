package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidade.Categoria;
import entidade.Cliente;
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario)"
                    + " VALUES (?,?,?,?,?,?)");
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
                    "SELECT \n" +
"                                compras.id as compras_id, compras.quantidade_compra, compras.data_compra, compras.valor_compra, \n" +
"                                compras.id_fornecedor, compras.id_produto, compras.id_funcionario,\n" +
"                                fornecedores.id as fornecedores_id, fornecedores.razao_social as nome_fornecedor, fornecedores.cnpj, fornecedores.endereco, fornecedores.bairro, \n" +
"                                fornecedores.cidade, fornecedores.uf, fornecedores.cep, fornecedores.telefone, fornecedores.email,\n" +
"                                produtos.id as produtos_id, produtos.nome_produto, produtos.descricao, produtos.preco_compra, \n" +
"                                produtos.preco_venda, produtos.quantidade_disponível, produtos.liberado_venda , produtos.id_categoria,\n" +
"                                funcionarios.id as funcionarios_id, funcionarios.nome as nome_funcionario, funcionarios.cpf as cpf_funcionario, \n" +
"                                funcionarios.senha, funcionarios.papel, funcionarios.email as funcionario_email,\n" +
"                                categorias.id as categorias_id, categorias.nome_categoria\n" +
"                                FROM \n" +
"                                    compras\n" +
"                                JOIN \n" +
"                                    fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
"                                JOIN \n" +
"                                    produtos ON compras.id_produto = produtos.id\n" +
"                                JOIN \n" +
"                                    funcionarios ON compras.id_funcionario = funcionarios.id\n" +
"                                JOIN \n" +
"                                    categorias ON produtos.id_categoria = categorias.id\n"+
                                "WHERE compras.id = ?");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Compra compra = new Compra();
            if (resultado != null) {
                while (resultado.next()) {
                    compra.setQuantidade(resultado.getInt("QUANTIDADE_COMPRA"));
                    compra.setData(resultado.getDate("DATA_COMPRA"));
                    compra.setValor(resultado.getInt("VALOR_COMPRA"));
                    compra.setId(resultado.getInt("COMPRAS_ID"));

                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(resultado.getInt("ID_FORNECEDOR"));
                    fornecedor.setRazaoSocial(resultado.getString("NOME_FORNECEDOR"));
                    fornecedor.setCnpj(resultado.getString("CNPJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro ( resultado.getString("BAIRRO"));
                    fornecedor.setCidade( resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP")); 
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL"));
                    
                    compra.setFornecedor(fornecedor);

                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID_PRODUTO"));
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
                    compra.setProduto(produto);

                    Funcionario comprador = new Funcionario();
                    comprador.setId(resultado.getInt("ID_FUNCIONARIO"));
                    comprador.setNome(resultado.getString("NOME_FUNCIONARIO"));
                    comprador.setCpf(resultado.getString("CPF_FUNCIONARIO"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    comprador.setPapel(Papel.values()[intPapel]);
                    comprador.setSenha(resultado.getString("SENHA"));
                    comprador.setEmail(resultado.getString("FUNCIONARIO_EMAIL"));
                    compra.setComprador(comprador);
                }
                
            }
            return compra;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (Compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    public Compra getPrimeiraCompraProduto(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, funcionarios.email as email_funcionario, fornecedores.email as email_fornecedor\n" +
                    "FROM compras \n" +
                    "JOIN fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
                    "JOIN produtos ON compras.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON compras.id_funcionario = funcionarios.id \n" +
                    "JOIN categorias ON categorias.id = produtos.id_categoria \n" +
                    "WHERE compras.id_produto = ?;");
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
            throw new RuntimeException("Query de select (Compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public Compra getPrimeiraCompraFuncionario(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, funcionarios.email as email_funcionario, fornecedores.email as email_fornecedor\n" +
                    "FROM compras \n" +
                    "JOIN fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
                    "JOIN produtos ON compras.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON compras.id_funcionario = funcionarios.id \n" +
                    "JOIN categorias ON categorias.id = produtos.id_categoria \n" +
                    "WHERE compras.id_funcionario = ?;");
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
            throw new RuntimeException("Query de select (Compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
        public Compra getPrimeiraCompraFornecedor(int id) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "SELECT *, funcionarios.email as email_funcionario, fornecedores.email as email_fornecedor\n" +
                    "FROM compras \n" +
                    "JOIN fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
                    "JOIN produtos ON compras.id_produto = produtos.id\n" +
                    "JOIN funcionarios ON compras.id_funcionario = funcionarios.id \n" +
                    "JOIN categorias ON categorias.id = produtos.id_categoria \n" +
                    "WHERE compras.id_fornecedor = ?;");
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
            throw new RuntimeException("Query de select (Compra) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE compras SET quantidade_compra = ?, data_compra = ?, valor_compra = ?, id_fornecedor = ?, id_produto = ?, id_funcionario = ? WHERE ID = ? ");
            sql.setInt(1, compra.getQuantidade());
            sql.setDate(2, compra.getData());
            sql.setInt(3, compra.getValor());
            sql.setInt(4, compra.getFornecedor().getId());
            sql.setInt(5, compra.getProduto().getId());
            sql.setInt(6, compra.getComprador().getId());
            sql.setInt(7, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (Compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Excluir(Compra compra) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM compras WHERE ID = ? ");
            sql.setInt(1, compra.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (Compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
     public ArrayList<Compra> ListaDeCompras() {
        ArrayList<Compra> minhasCompras = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT \n" +
"                                compras.id as compras_id, compras.quantidade_compra, compras.data_compra, compras.valor_compra, \n" +
"                                compras.id_fornecedor, compras.id_produto, compras.id_funcionario,\n" +
"                                fornecedores.id as fornecedores_id, fornecedores.razao_social as nome_fornecedor, fornecedores.cnpj, fornecedores.endereco, fornecedores.bairro, \n" +
"                                fornecedores.cidade, fornecedores.uf, fornecedores.cep, fornecedores.telefone, fornecedores.email,\n" +
"                                produtos.id as produtos_id, produtos.nome_produto, produtos.descricao, produtos.preco_compra, \n" +
"                                produtos.preco_venda, produtos.quantidade_disponível, produtos.liberado_venda , produtos.id_categoria,\n" +
"                                funcionarios.id as funcionarios_id, funcionarios.nome as nome_funcionario, funcionarios.cpf as cpf_funcionario, \n" +
"                                funcionarios.senha, funcionarios.papel, funcionarios.email as funcionario_email,\n" +
"                                categorias.id as categorias_id, categorias.nome_categoria\n" +
"                                FROM \n" +
"                                    compras\n" +
"                                JOIN \n" +
"                                    fornecedores ON compras.id_fornecedor = fornecedores.id\n" +
"                                JOIN \n" +
"                                    produtos ON compras.id_produto = produtos.id\n" +
"                                JOIN \n" +
"                                    funcionarios ON compras.id_funcionario = funcionarios.id\n" +
"                                JOIN \n" +
"                                    categorias ON produtos.id_categoria = categorias.id";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Compra compra = new Compra();
                    compra.setQuantidade(resultado.getInt("QUANTIDADE_COMPRA"));
                    compra.setData(resultado.getDate("DATA_COMPRA"));
                    compra.setValor(resultado.getInt("VALOR_COMPRA"));
                    compra.setId(resultado.getInt("COMPRAS_ID"));

                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(resultado.getInt("ID_FORNECEDOR"));
                    fornecedor.setRazaoSocial(resultado.getString("NOME_FORNECEDOR"));
                    fornecedor.setCnpj(resultado.getString("CNPJ"));
                    fornecedor.setEndereco(resultado.getString("ENDERECO"));
                    fornecedor.setBairro ( resultado.getString("BAIRRO"));
                    fornecedor.setCidade( resultado.getString("CIDADE"));
                    fornecedor.setUf(resultado.getString("UF"));
                    fornecedor.setCep(resultado.getString("CEP")); 
                    fornecedor.setTelefone(resultado.getString("TELEFONE"));
                    fornecedor.setEmail(resultado.getString("EMAIL"));
                    
                    compra.setFornecedor(fornecedor);

                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("ID_PRODUTO"));
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
                    compra.setProduto(produto);

                    Funcionario comprador = new Funcionario();
                    comprador.setId(resultado.getInt("ID_FUNCIONARIO"));
                    comprador.setNome(resultado.getString("NOME_FUNCIONARIO"));
                    comprador.setCpf(resultado.getString("CPF_FUNCIONARIO"));
                    int intPapel = Integer.parseInt(resultado.getString("Papel"));
                    comprador.setPapel(Papel.values()[intPapel]);
                    comprador.setSenha(resultado.getString("SENHA"));
                    comprador.setEmail(resultado.getString("FUNCIONARIO_EMAIL"));
                    
                    compra.setComprador(comprador);
                    minhasCompras.add(compra);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select <ListaDeFuncionarios> (Compras) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasCompras;
    }
  }
