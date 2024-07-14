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
        <title>Lista Funcionários</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
        </div>
        <div class="container">
        <h1 class="mt-5">Lista de Funcionários</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Papel</th>
                    <th>Senha</th>
                    <th>E-mail</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                    List<Funcionario> funcionarios = funcionarioDAO.ListaDeFuncionarios();
                    for (Funcionario funcionario : funcionarios) {
                %>
                <tr>
                    <td><%= funcionario.getNome() %></td>
                    <td><%= funcionario.getCpf() %></td>
                    <td><%= funcionario.getPapel() %></td>
                    <td><%= funcionario.getSenha() %></td>
                    <td><%= funcionario.getEmail() %></td>
                    <td>
                        <form action="/aplicacaoMVC/funcionario/alterar" method="get" style="display:inline;">
                            <input type="hidden" name="id" value="<%= funcionario.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="/aplicacaoMVC/funcionario/excluir" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= funcionario.getId() %>"/>
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