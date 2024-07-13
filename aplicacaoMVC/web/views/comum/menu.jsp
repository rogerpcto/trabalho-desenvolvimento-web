<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
                        if (funcionarioLogado == null) { 

                    %>
                        <a class="nav-link" href="/aplicacaoMVC/ProdutosController">Produtos</a>
                        <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                            
                <%  }
                    else {
                        switch (funcionarioLogado.getPapel()){
                            case ADMINISTRADOR:
                %>
                            
                            <a class="nav-link" href="/aplicacaoMVC/CadastrarFuncionario">Cadastrar Funcionário</a>
                <%
                            break;
                            case VENDEDOR:
                %>
                            <a class="nav-link" href="/aplicacaoMVC/CadastrarCliente">Cadastrar Cliente</a>
                <%
                                break;
                            case COMPRADOR:
                %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/CategoriaController?acao=Listar">Categorias</a>

                <%
                                break;
                        }
                %>
                    <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%
                    }
                }
                %>
            </div>
        </div>
    </div>
</nav>
