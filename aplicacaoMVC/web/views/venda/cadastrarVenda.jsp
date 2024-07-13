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
    <script>
    function calcularValorVenda() {
        const precoVenda = parseFloat(document.getElementById('preco_venda').value);
        const quantidadeDisponivel = parseInt(document.getElementById('quantidade_disponivel').innerText);
        const quantidadeVendaInput = document.getElementById('quantidade_venda');
        let quantidadeVenda = quantidadeVendaInput.value.trim(); // Remover espaços em branco

        // Verificar se o campo está vazio
        if (quantidadeVenda === '') {
            document.getElementById('valor_venda').value = ''; // Limpar valor da venda
            return; // Sai da função se estiver vazio
        }

        // Verificar se a quantidade é um número válido e está dentro do limite disponível
        quantidadeVenda = parseInt(quantidadeVenda);
        if (isNaN(quantidadeVenda) || quantidadeVenda <= 0 || quantidadeVenda > quantidadeDisponivel) {
            alert("Digite uma quantidade válida entre 1 e " + quantidadeDisponivel);
            quantidadeVendaInput.value = ''; // Limpar campo de quantidade
            document.getElementById('valor_venda').value = ''; // Limpar valor da venda
            return;
        }

        const valorVenda = precoVenda * quantidadeVenda;
        document.getElementById('valor_venda').value = valorVenda.toFixed(2);
    }
</script>
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="VendasController" method="post">
                <div class="mb-3">
                    <% 
                        Produto produto = (Produto) request.getAttribute("produto");
                        if (produto != null) {
                    %>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Produto: <%= produto.getNomeProduto() %></h5>
                            <p class="card-text">Descrição: <%= produto.getDescricao() %></p>
                            <p class="card-text">Preço: R$ <%= produto.getPrecoVenda() %></p>
                            <p class="card-text">Quantidade Disponível: <span id="quantidade_disponivel"><%= produto.getQuantidadeDisponivel() %></span></p>
                            <input type="hidden" id="preco_venda" value="<%= produto.getPrecoVenda() %>">
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
                    <label for="quantidade_venda" class="form-label">Quantidade</label>
                    <input required
                           maxlength="5" minlength="1"
                           type="number"
                           class="form-control"
                           name="quantidade_venda"
                           id="quantidade_venda"
                           aria-describedby="helpId"
                           oninput="calcularValorVenda()"
                    />
                    <small id="helpId" class="form-text text-muted">Quantidade da venda (máximo: <%= produto != null ? produto.getQuantidadeDisponivel() : 0 %>)</small>
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
                           readonly
                    />
                    <small id="helpId" class="form-text text-muted">Valor da venda</small>
                </div>
                <div class="mb-3">
                    <label for="data_venda" class="form-label">Data</label>
                    <input required
                           maxlength="10" minlength="10"
                           type="date"
                           class="form-control"
                           name="data_venda"
                           id="data_venda"
                           aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Data da venda</small>
                </div>
                <div class="mb-3">
                    <label for="Cliente" class="form-label">Cliente</label>
                    <select class="form-select" name="id_cliente" id="id_cliente" required aria-describedby="helpId">
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
                    <select class="form-select" name="id_funcionario" id="id_funcionario" required aria-describedby="helpId">
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
                <button type="submit" class="btn btn-primary">Vender</button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
