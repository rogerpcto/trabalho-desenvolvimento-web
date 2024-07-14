<%@page import="entidade.Produto"%>
<%@page import="entidade.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="model.CategoriaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="col-sm-6 mt-5 mb-5">
            <form action="alterarProduto" method="post">
                <%
                    Produto produto = (Produto) request.getAttribute("produto");
                    %>
                <div class="mb-3">
                    <label for="nome_produtos" class="form-label">Nome do Produto</label>
                    <input required
                        maxlength="255"
                        type="text"
                        class="form-control"
                        name="nome_produto"
                        id="nome_produto"
                        aria-describedby="helpId"
                        value="<%=produto.getNomeProduto()%>"
                    />
                    <small id="helpId" class="form-text text-muted">Nome do Produto</small>
                    <input type="hidden" name="id_produto" value="<%= produto.getId() %>">
                </div>
                <div class="mb-3">
                <label for="descricao" class="form-label">Descrição</label>
                    <input required
                        maxlength="5" minlength="1"
                        type="text"
                        class="form-control"
                        name="descricao"
                        id="descricao"
                        aria-describedby="helpId"
                        value="<%=produto.getDescricao()%>"

                    />
                    <small id="helpId" class="form-text text-muted">Descrição</small>
                </div>
                 <div class="mb-3">
    <label for="categoria" class="form-label">Categoria</label>
    <select class="form-select" name="categoria" id="categoria" required aria-describedby="helpId">
        <%
            // Consultando as categorias do banco de dados
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> categorias = categoriaDAO.ListaDeCategorias();
            int categoriaProduto = produto.getCategoria().getId(); // Obtém o ID da categoria do produto

            // Gerando as opções do dropdown
            for (Categoria categoria : categorias) {
                String selected = categoria.getId() == categoriaProduto ? "selected" : "";
        %>
                <option value="<%= categoria.getId() %>" <%= selected %>><%= categoria.getNomeCategoria() %></option>
        <%
            }
        %>
    </select>
    <small id="helpId" class="form-text text-muted">Categoria</small>
</div>

       
                <div class="mb-3">
                    <label for="preco_compra" class="form-label">Preço de Compra</label>
                    <input required
                        maxlength="50" minlength="1"
                        type="text"
                        class="form-control"
                        name="preco_compra"
                        id="preco_compra"
                        aria-describedby="helpId"
                        value="<%=produto.getPrecoCompra()%>"
                    />
                    <small id="helpId" class="form-text text-muted">Preço de Compra</small>
                </div>
                <div class="mb-3">
                    <label for="preco_venda" class="form-label">Preço de Venda</label>
                    <input required
                        maxlength="50" minlength="1"
                        type="text"
                        class="form-control"
                        name="preco_venda"
                        id="preco_venda"
                        aria-describedby="helpId"
                        value="<%=produto.getPrecoVenda()%>"
                    />
                    <small id="helpId" class="form-text text-muted">Preço de Venda</small>
                </div>
                <div class="mb-3">
                    <label for="quantidade_disponivel" class="form-label">Quantidade Disponível</label>
                    <input required
                        maxlength="5" minlength="1"
                        type="text"
                        class="form-control"
                        name="quantidade_disponivel"
                        id="quantidade_disponivel"
                        aria-describedby="helpId"
                        value="<%=produto.getQuantidadeDisponivel() %>"
                    />
                    <small id="helpId" class="form-text text-muted">Quantidade Disponível</small>
                </div>
                <div class="mb-3">
                    <input class="form-check-input" type="checkbox" value="true" id="liberar_venda" name="liberado_venda">
                    <label class="form-check-label" for="liberar_venda">
                        Liberar Venda
                    </label>
                </div>
                <button type="submit" class="btn btn-primary">
                    Entrar
                </button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
