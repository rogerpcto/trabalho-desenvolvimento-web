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
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <%
                    Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                    out.println("<h3>Comprador logado com sucesso</h3>");
                    out.println("<h2>Nome: " + funcionarioLogado.getNome() + "</h2>");
                %>
            </div>
        </div>
        <div class="container">
        <h1 class="mt-5">Lista de Produtos</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço de Compra</th>
                    <th>Preço de Venda</th>
                    <th>Quantidade Disponível</th>
                    <th>Liberado para Venda</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ProdutoDAO produtoDAO = new ProdutoDAO();
                    List<Produto> produtos = produtoDAO.ListaDeProdutos();
                    for (Produto produto : produtos) {
                    if (produto.getQuantidadeDisponivel() > 0){
                %>
                <tr>
                    <td><%= produto.getId() %></td>
                    <td><%= produto.getNomeProduto() %></td>
                    <td><%= produto.getDescricao() %></td>
                    <td><%= produto.getPrecoCompra() %></td>
                    <td><%= produto.getPrecoVenda() %></td>
                    <td><%= produto.getQuantidadeDisponivel() %></td>
                    <td><%= produto.getLiberadoVenda().equals("S") ? "Sim" : "Não" %></td>
                    <td>
                        <form action="liberar" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= produto.getId() %>"/>
                            <button type="submit" class="btn btn-danger btn-sm"><%= produto.getLiberadoVenda().equals("S") ? "Bloquear" : "Liberar" %></button>
                        </form>
                        <form action="alterarProduto.jsp" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= produto.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="excluirProduto" method="post" style="display:inline;">
                            <input type="hidden" name="id_produto" value="<%= produto.getId() %>"/>
                            <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                        </form>
                        
                    </td>
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
