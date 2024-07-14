<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@page import="model.ProdutoDAO"%>
<%@page import="entidade.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Estoque</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
            </div>
        </div>
        <div class="container">
        <h1 class="mt-5">Estoque</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Quantidade Disponível</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    List<Produto> produtos = produtoDAO.ListaDeProdutos();
                    Collections.sort(produtos, new Comparator<Produto>() {
                        @Override
                        public int compare(Produto p1, Produto p2) {
                            return Integer.compare(p2.getQuantidadeDisponivel(), p1.getQuantidadeDisponivel());
                        }
                    });
                    for (Produto produto : produtos) {
                %>
                <tr>
                    <td><%= produto.getId() %></td>
                    <td><%= produto.getNomeProduto() %></td>
                    <td><%= produto.getQuantidadeDisponivel() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>