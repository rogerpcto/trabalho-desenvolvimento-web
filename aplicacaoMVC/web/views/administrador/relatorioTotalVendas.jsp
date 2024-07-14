<%@page import="model.VendaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@page import="model.VendasDAO"%>
<%@page import="entidade.Produto"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
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
        <h1 class="mt-5">Total de Vendas</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Quantidade Vendido</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Date data = (Date) request.getAttribute("data");
                    VendaDAO vendaDAO = new VendaDAO();
                    List<Produto> produtos = vendaDAO.TotalVendas(data);
                    for (Produto produto : produtos) {
                %>
                <tr>
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