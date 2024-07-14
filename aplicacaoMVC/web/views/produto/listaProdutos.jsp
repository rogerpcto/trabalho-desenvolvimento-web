<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@page import="model.ProdutoDAO"%>
<%@page import="entidade.Produto"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Área Restrita</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
        </div>
        <div class="container">
        <h1 class="mt-5">Lista de Produtos</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço de Compra</th>
                    <th>Preço de Venda</th>
                    <th>Quantidade Disponível</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    List<Produto> produtos = produtoDAO.ListaDeProdutos();
                    for (Produto produto : produtos) {
                    if (produto.getQuantidadeDisponivel() > 0 && produto.getLiberadoVenda().equals("S")){
                %>
                <tr>
                    <td><%= produto.getNomeProduto() %></td>
                    <td><%= produto.getDescricao() %></td>
                    <td><%= produto.getPrecoCompra() %></td>
                    <td><%= produto.getPrecoVenda() %></td>
                    <td><%= produto.getQuantidadeDisponivel() %></td>
                </tr>
                <%
                    }
                }        
                %>
            </tbody>
        </table>
    </div>
</body>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
