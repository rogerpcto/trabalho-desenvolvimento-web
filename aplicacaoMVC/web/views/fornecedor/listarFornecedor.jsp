<%@page import="entidade.Fornecedor"%>
<%@page import="model.FornecedorDAO"%>
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
        <h1 class="mt-5">Lista de Fornecedores</h1>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Razão Social</th>
                    <th>CNPJ</th>
                    <th>Endereço</th>
                    <th>Bairro</th>
                    <th>Cidade</th>
                    <th>UF</th>
                    <th>CEP</th>
                    <th>Telefone</th>
                    <th>email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    FornecedorDAO fornecedorDAO = new FornecedorDAO();
                    List<Fornecedor> fornecedores = fornecedorDAO.ListaDeFornecedores();
                    for (Fornecedor fornecedor : fornecedores) {
                %>
                <tr>
                    <td><%= fornecedor.getId() %></td>
                    <td><%= fornecedor.getRazaoSocial()%></td>
                    <td><%= fornecedor.getCnpj()%></td>
                    <td><%= fornecedor.getEndereco()%></td>
                    <td><%= fornecedor.getBairro()%></td>
                    <td><%= fornecedor.getCidade() %></td>
                    <td><%= fornecedor.getUf() %></td>
                    <td><%= fornecedor.getCep()%></td>
                    <td><%= fornecedor.getTelefone()%></td>
                    <td><%= fornecedor.getEmail()%></td>
                    <td>
                        <form action="alterarFornecedor" method="get" style="display:inline;">
                            <input type="hidden" name="id_fornecedor" value="<%= fornecedor.getId() %>"/>
                            <button type="submit" class="btn btn-primary btn-sm">Alterar</button>
                        </form>
                        <form action="excluirFornecedor" method="post" style="display:inline;">
                            <input type="hidden" name="id_fornecedor" value="<%= fornecedor.getId() %>"/>
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
