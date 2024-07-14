<%@page import="entidade.Cliente"%>
<%@page import="model.ClienteDAO"%>
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
    </div>
    <div class="container">
        <h1 class="mt-5">Lista de Clientes</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Endereço</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>UF</th>
                    <th>CEP</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    ClienteDAO clienteDAO = new ClienteDAO();
                    List<Cliente> clientes = clienteDAO.ListaDeClientes();
                    for (Cliente cliente : clientes) {
                %>
                <tr>
                    <td><%= cliente.getNome() %></td>
                    <td><%= cliente.getCpf() %></td>
                    <td><%= cliente.getEndereco() %></td>
                    <td><%= cliente.getBairro() %></td>
                    <td><%= cliente.getCidade() %></td>
                    <td><%= cliente.getUf() %></td>
                    <td><%= cliente.getCep() %></td>
                    <td><%= cliente.getTelefone() %></td>
                    <td><%= cliente.getEmail() %></td>
                    <td>
                        <form action="/aplicacaoMVC/vendedor/alterarCliente" method="get" style="display:inline;">
                            <input type="hidden" name="id_cliente" value="<%= cliente.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="excluirCliente" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= cliente.getId() %>"/>
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
