<%@page import="entidade.Compra"%>
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
    function calcularValorcompra() {
        const precocompra = parseFloat(document.getElementById('preco_compra').value);
        const quantidadecompraInput = document.getElementById('quantidade_compra');
        let quantidadecompra = quantidadecompraInput.value.trim(); // Remover espaços em branco

        // Verificar se o campo está vazio
        if (quantidadecompra === '') {
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return; // Sai da função se estiver vazio
        }

        // Verificar se a quantidade é um número válido
        quantidadecompra = parseInt(quantidadecompra);
        if (isNaN(quantidadecompra) || quantidadecompra <= 0) {
            alert("Digite uma quantidade válida.");
            quantidadecompraInput.value = ''; // Limpar campo de quantidade
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return;
        }

        const valorcompra = precocompra * quantidadecompra;
        document.getElementById('valor_compra').value = parseInt(valorcompra);
    }
    </script>
</head>
<body>
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="col-sm-6 mt-5 mb-5">
            <form action="alterarCompra" method="post">
                <div class="mb-3">
                    <div class="mb-3">
                    <label for="Funcionario" class="form-label">Produto</label>
                     <% 
                                Compra compra = (Compra) request.getAttribute("compra");
                        %>
                    <input type="text" class="form-control" id="nome_produto" name="nome_produto" value="<%= compra.getProduto().getNomeProduto()%>" disabled>
                    <small id="helpId" class="form-text text-muted">Produto</small>
                </div>
                    <input type="hidden" id="id_produto" name="id_produto" value="<%= compra.getProduto().getId()%>">
                    <input type="hidden" id="preco_compra" name="preco_compra" value="<%= compra.getProduto().getPrecoCompra() %>">
                    <input type="hidden" id="id_compra" name="id_compra" value="<%=compra.getId()%>">
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
                           oninput="calcularValorcompra()"
                           value="<%= compra.getQuantidade()%>"
                    />
                    <small id="helpId" class="form-text text-muted"></small>
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
                           value="<%= compra.getValor()%>"
                           readonly
                    />
                    <small id="helpId" class="form-text text-muted">Valor da compra</small>
                </div>
                <div class="mb-3">
                    <label for="data_compra" class="form-label">Data</label>
                    <input required
                           maxlength="10" minlength="10"
                           type="date"
                           class="form-control"
                           name="data_compra"
                           id="data_compra"
                           aria-describedby="helpId"
                           value="<%=compra.getData()%>"
                    />
                    <small id="helpId" class="form-text text-muted">Data da compra</small>
                </div>
                <div class="mb-3">
                    <label for="Fornecedor" class="form-label">Fornecedor</label>
                    <select class="form-select" name="id_fornecedor" id="id_fornecedor" required aria-describedby="helpId">
                        <% 
                            try {
                                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                                List<Fornecedor> fornecedores = fornecedorDAO.ListaDeFornecedores();
                                for (Fornecedor fornecedor : fornecedores) {
                        %>
                        <option value="<%= fornecedor.getId() %>">
                            <%= compra.getFornecedor().getRazaoSocial() %>
                        </option>
                        <% 
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Fornecedor</small>
                </div>
                <div class="mb-3">
                    <label for="Funcionario" class="form-label">Funcionário</label>
                    <%
                        Funcionario funcionario = null;
                        if (session != null) {
                            funcionario = (Funcionario) session.getAttribute("funcionario");
                        }
                    %>
                    <input type="text" class="form-control" id="funcionario" name="funcionario" value="<%= funcionario.getNome() %>" disabled>
                    <small id="helpId" class="form-text text-muted">Funcionário</small>
                </div>
                <button type="submit" class="btn btn-primary">Alterar</button>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
