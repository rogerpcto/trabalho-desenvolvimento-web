<%@page import="entidade.Compra"%>
<%@page import="model.CompraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@page import="model.CompraDAO"%>
<%@page import="entidade.Compra"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Comprador</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
        <style>
            .aviso {
                background-color: red;
                color: white;
                padding: 15px;
                font-size: 24px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">
                <% 
                    String mensagem = (String) request.getAttribute("mensagem");
                    if (mensagem != null) {
                %>
                <div class="aviso">
                    <%= mensagem %>
                </div>
                <h1>Área Restrita</h1>
                <%
                    }
                    Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                %>
            </div>
        </div>
        <div class="container">
        <h1 class="mt-5">Lista de compras</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Quantidade</th>
                    <th>Data</th>
                    <th>Valor da Compra</th>
                    <th>Fornecedor</th>
                    <th>Produto</th>
                    <th>Comprador</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    CompraDAO compraDAO = new CompraDAO();
                    List<Compra> compras = compraDAO.ListaDeCompras();
                    for (Compra compra : compras) {
                %>
                <tr>
                    <td><%= compra.getId() %></td>
                    <td><%= compra.getQuantidade()%></td>
                    <td><%= compra.getData()%></td>
                    <td><%= compra.getValor()%></td>
                    <td><%= compra.getFornecedor().getRazaoSocial()%></td>
                    <td><%= compra.getProduto().getNomeProduto() %></td>
                    <td><%= compra.getComprador().getNome()%></td>
                    <td>
                        <form action="liberar" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= compra.getId() %>"/>
                        </form>
                        <form action="alterarcompra" method="get" style="display:inline;">
                            <input type="hidden" name="id_compra" value="<%= compra.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="excluircompra" method="post" style="display:inline;">
                            <input type="hidden" name="id_compra" value="<%= compra.getId() %>"/>
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
</body>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
