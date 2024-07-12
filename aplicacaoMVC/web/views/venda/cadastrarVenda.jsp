<%@page import="entidade.Funcionario"%>
<%@page import="model.FuncionarioDAO"%>
<%@page import="entidade.Cliente"%>
<%@page import="model.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Produto"%>
<%@page import="model.ProdutoDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="processa_venda.jsp" method="post">
                <div class="mb-3">
                    <label for="Produto" class="form-label">Produto</label>
                    <select class="form-select" name="Produto" id="Produto" required aria-describedby="helpId">
                        <%
                            ProdutoDAO produtoDAO = new ProdutoDAO();
                            List<Produto> produtos = produtoDAO.ListaDeProdutos();

                            for (Produto produto : produtos) {
                        %>
                            <option value="<%= produto.getId() %>"><%= produto.getNomeProduto() %></option>
                        <%
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Produto</small>
                </div>
                <div class="mb-3">
                    <label for="quantidade_venda" class="form-label">Quantidade</label>
                    <input required
                        maxlength="5" minlength="1"
                        type="text"
                        class="form-control"
                        name="quantidade_venda"
                        id="quantidade_venda"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Quantidade da venda</small>
                </div>
                <div class="mb-3">
                    <label for="valor_venda" class="form-label">Valor</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="valor_venda"
                        id="valor_venda"
                        aria-describedby="helpId"
             
                    />
                    <small id="helpId" class="form-text text-muted">Valor da venda</small>
                </div>
                <div class="mb-3">
                    <label for="data_venda" class="form-label">Data</label>
                    <input required
                        maxlength="10" minlength="10"
                        type="text"
                        class="form-control"
                        name="data_venda"
                        id="data_venda"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Data da venda</small>
                </div>
                <div class="mb-3">
                    <label for="Cliente" class="form-label">Cliente</label>
                    <select class="form-select" name="Cliente" id="Cliente" required aria-describedby="helpId">
                        <%
                            ClienteDAO clienteDAO = new ClienteDAO();
                            List<Cliente> clientes = clienteDAO.ListaDeClientes();

                            for (Cliente cliente : clientes) {
                        %>
                            <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
                        <%
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="Funcionario" class="form-label">Funcionário</label>
                    <select class="form-select" name="Funcionario" id="Funcionario" required aria-describedby="helpId">
                        <%
                            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                            List<Funcionario> funcionarios = funcionarioDAO.ListaDeVendedores();

                            for (Funcionario funcionario : funcionarios) {
                        %>
                            <option value="<%= funcionario.getId() %>"><%= funcionario.getNome() %></option>
                        <%
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Funcionário</small>
                </div>
                <button
                    type="submit"
                    class="btn btn-primary"
                >
                Entrar
                </button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
        function calcularValorVenda() {
            var produtoSelect = document.getElementById("Produto");
            var quantidadeInput = document.getElementById("quantidade_venda");
            var valorVendaInput = document.getElementById("valor_venda");

            var produtoId = produtoSelect.value;
            var quantidade = parseInt(quantidadeInput.value);

            if (!isNaN(quantidade)) {
                // Aqui você precisará obter o preço do produto com base no produtoId selecionado
                // Supondo que você tenha acesso a uma lista de produtos e seus preços
                var precoProduto = obterPrecoProduto(produtoId); // Função a ser implementada para obter o preço do produto

                if (precoProduto) {
                    var valorVenda = precoProduto * quantidade;
                    valorVendaInput.value = valorVenda.toFixed(2); // Formata para 2 casas decimais, se necessário
                }
            }
        }

        function obterPrecoProduto(produtoId) {
            // Implemente a lógica para buscar o preço do produto com base no ID
            // Você pode buscar em uma lista de produtos ou fazer uma requisição ao backend
            // Exemplo simplificado:
            var produtos = <%= new ProdutoDAO().ListaDeProdutos() %>; // Supondo que tenha um método assim no seu DAO
            console.log(produtos)
            for (var i = 0; i < produtos.length; i++) {
                if (produtos[i].getId === produtoId) {
                    return produtos[i].getPreco; // Supondo que o objeto Produto tenha um atributo 'preco'
                }
            }

            return null; // Caso não encontre o preço do produto
        }
    </script>
</html>
