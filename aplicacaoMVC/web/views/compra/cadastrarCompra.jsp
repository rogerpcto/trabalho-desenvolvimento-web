<%@page import="entidade.Funcionario"%>
<%@page import="model.FuncionarioDAO"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="model.FornecedorDAO"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Produto"%>
<%@page import="model.ProdutoDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script>
    function calcularValorCompra() {
        const precoVenda = parseFloat(document.getElementById('preco_compra').value);
        const quantidadeDisponivel = parseInt(document.getElementById('quantidade_disponivel').innerText);
        const quantidadeCompraInput = document.getElementById('quantidade_compra');
        let quantidadeCompra = quantidadeCompraInput.value.trim(); // Remover espaços em branco

        // Verificar se o campo está vazio
        if (quantidadeCompra === '') {
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return; // Sai da função se estiver vazio
        }

        // Verificar se a quantidade é um número válido e está dentro do limite disponível
        quantidadeCompra = parseInt(quantidadeCompra);
        if (isNaN(quantidadeCompra) || quantidadeCompra <= 0 || quantidadeCompra > quantidadeDisponivel) {
            alert("Digite uma quantidade válida entre 1 e " + quantidadeDisponivel);
            quantidadeCompraInput.value = ''; // Limpar campo de quantidade
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return;
        }

        const valorCompra = precoCompra * quantidadeCompra;
        document.getElementById('valor_compra').value = valorCompra.toFixed(2);
    }
</script>
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="ComprasController" method="post">
                <div class="mb-3">
                    <% 
                        Produto produto = (Produto) request.getAttribute("produto");
                        if (produto != null) {
                    %>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Produto: <%= produto.getNomeProduto() %></h5>
                            <p class="card-text">Descrição: <%= produto.getDescricao() %></p>
                            <p class="card-text">Preço: R$ <%= produto.getPrecoCompra() %></p>
                            <p class="card-text">Quantidade Disponível: <span id="quantidade_disponivel"><%= produto.getQuantidadeDisponivel() %></span></p>
                            <input type="hidden" id="preco_compra" value="<%= produto.getPrecoCompra() %>">
                            <input type="hidden" name="id_produto" value="<%= produto.getId() %>">
                        </div>
                    </div>
                    <% 
                        } else { 
                    %>
                    <p>Produto não encontrado.</p>
                    <% 
                        } 
                    %>
                </div>
                <div class="mb-3">
                    <label for="quantidade_compra" class="form-label">Quantidade</label>
                    <input required
                           maxlength="5" minlength="1"
                           type="number"
                           class="form-control"
                           name="quantidade_compra"
                           id="quantidade_compra"
                           aria-describedby="helpId"
                           oninput="calcularValorCompra()"
                    />
                    <small id="helpId" class="form-text text-muted">Quantidade da Compra (máximo: <%= produto != null ? produto.getQuantidadeDisponivel() : 0 %>)</small>
                </div>
                <div class="mb-3">
                    <label for="valor_compra" class="form-label">Valor</label>
                    <input required
                           maxlength="50" minlength="3"
                           type="text"
                           class="form-control"
                           name="valor_compra"
                           id="valor_compra"
                           aria-describedby="helpId"
                           readonly
                    />
                    <small id="helpId" class="form-text text-muted">Valor da Compra</small>
                </div>
                <div class="mb-3">
                    <label for="data_compra" class="form-label">Data</label>
                    <input required
                           maxlength="10" minlength="10"
                           type="text"
                           class="form-control"
                           name="data_compra"
                           id="data_compra"
                           aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Data da Compra</small>
                </div>
                <div class="mb-3">
                    <label for="Cliente" class="form-label">Cliente</label>
                    <select class="form-select" name="cliente" id="cliente" required aria-describedby="helpId">
                        <%
                            ClienteDAO ClienteDAO = new ClienteDAO();
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
                    <select class="form-select" name="funcionario" id="funcionario" required aria-describedby="helpId">
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
                <button type="submit" class="btn btn-primary">Comprar</button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
