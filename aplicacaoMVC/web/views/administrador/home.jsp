<%@page import="entidade.Funcionario"%>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>√Årea Restrita</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../comum/menu.jsp" />
            <div class="mt-5">

                <h1>¡rea Restrita</h1>
                <%
                    Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                    out.println("<h3>Administrador logado com sucesso</h3>");
                    out.println("<h2>Nome: " + funcionarioLogado.getNome() + "</h2>");
                %>
            </div>
        </div>
        <div class="container">
            <div class="mt-3">
                <a href="/aplicacaoMVC/funcionarios/listar" class="btn btn-primary">Lista Funcion·rios</a>
                <a href="" class="btn btn-primary">RelatÛrio Estoque</a>
                <a href="" class="btn btn-primary">RelatÛrio total de vendas</a>
            </div>
        </div>
    </body>
</html>
