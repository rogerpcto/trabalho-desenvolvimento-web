<%@page import="entidade.Categoria"%>
<%@page import="model.CategoriaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<%@page import="model.FuncionarioDAO"%>
<%@page import="entidade.Funcionario"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Categorias</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
        </div>
        <div class="container">
        <h1 class="mt-5">Lista de Categorias</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    CategoriaDAO categoriaDAO = new CategoriaDAO();
                    List<Categoria> categorias = categoriaDAO.ListaDeCategorias();
                    for (Categoria categoria : categorias) {
                %>
                <tr>
                    <td><%= categoria.getId() %></td>
                    <td><%= categoria.getNomeCategoria() %></td>
                    <td>
                        <form action="/aplicacaoMVC/categoria/alterar" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= categoria.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="/aplicacaoMVC/categoria/excluir" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= categoria.getId() %>"/>
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