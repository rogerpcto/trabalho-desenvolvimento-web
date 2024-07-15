<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Funcionario" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <%
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
            Funcionario funcionarioLogado = (Funcionario) session.getAttribute("funcionario");
            String url = "";
            if (funcionarioLogado != null) { 
                switch (funcionarioLogado.getPapel()){
                    
                    case ADMINISTRADOR:
                        url = "administrador/home";
                        break;
                
                    case COMPRADOR:
                        url = "comprador/home";
                        break;
                        
                    case VENDEDOR:
                        url = "vendedor/home";
                        break;    
                }
            }    
    %>
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/<%=url%>">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                if (funcionarioLogado == null) {
                %>
                    <a class="nav-link" href="/aplicacaoMVC/listar/produtos">Produtos</a>
                    <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    
                }
                else{
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
                        <a class="nav-link" href="/aplicacaoMVC/cadastrarCategoria">Cadastrar Categoria</a>
                        <a class="nav-link" href="/aplicacaoMVC/cadastrarFornecedor">Cadastrar Fornecedor</a>
                        <a class="nav-link" href="/aplicacaoMVC/ProdutosController">Cadastrar Produto</a>
                        <a class="nav-link" href="/aplicacaoMVC/cadastrarCompra">Cadastrar Compra</a>

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
