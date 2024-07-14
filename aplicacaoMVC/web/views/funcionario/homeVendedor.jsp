<%@ page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@ page import="model.ProdutoDAO" %>
<%@ page import="entidade.Produto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>Área Restrita</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="mt-5">
            <h1>Área Restrita</h1>
            <%
                Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                out.println("<h3>Vendedor logado com sucesso</h3>");
                out.println("<h2>Nome: " + funcionarioLogado.getNome() + "</h2>");
            %>
        </div>
    </div>
    <div class="container">
        <h1 class="mt-5">Lista de Produtos</h1>
        <div class="mt-3">
            <a href="listarCliente" class="btn btn-primary">Listar Cliente</a>
            <a href="listarProdutos" class="btn btn-primary">Listar Produtos</a>
            <a href="listarVendas" class="btn btn-primary">Listar Vendas</a>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>