<%@ page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@ page import="model.VendaDAO" %>
<%@ page import="entidade.Venda" %>
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
    </div>
    <div class="container">
        <h1 class="mt-5">Lista de Vendas</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Quantidade</th>
                    <th>Data</th>
                    <th>Valor</th>
                    <th>Cliente</th>
                    <th>Produto</th>
                    <th>Vendedor</th>
                </tr>
            </thead>
            <tbody>
                <%
                    VendaDAO vendaDAO = new VendaDAO();
                    List<Venda> vendas = vendaDAO.ListaDeVendas();
                    Funcionario funcionario = null;
                    
                        if (session != null) {
                            funcionario = (Funcionario) session.getAttribute("funcionario");
                        }
                    for (Venda venda : vendas) {
                        if(venda.getVendedor().getId() != funcionario.getId())
                            continue;
                %>
                <tr>
                    <td><%= venda.getQuantidade() %></td>
                    <td><%= venda.getData() %></td>
                    <td><%= venda.getValor() %></td>
                    <td><%= venda.getCliente().getNome() %></td>
                    <td><%= venda.getProduto().getNomeProduto()%></td>
                    <td><%= venda.getVendedor().getNome() %></td>
                    <td>
                        <a href="alterarVenda?id=<%= venda.getId() %>" class="btn btn-primary btn-sm">Alterar</a>
                        <form action="excluirVenda" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= venda.getId() %>"/>
                            <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
