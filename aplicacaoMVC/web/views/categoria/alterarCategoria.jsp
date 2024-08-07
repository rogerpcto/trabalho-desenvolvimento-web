<%@page import="entidade.Categoria"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categoria</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="col-sm-6 mt-5 mb-5">
            <form action="alterar" method="post">
                <div class="mb-3">
                    <% 
                        Categoria categoria = (Categoria) request.getAttribute("categoria");
                    %>
                    <input type="hidden" name="id" value="<%= categoria.getId()%>">
                    <label for="nome_categoria" class="form-label">Categoria</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="nome_categoria"
                        id="nome_categoria"
                        aria-describedby="helpId"
                        value="<%=categoria.getNomeCategoria()%>"
                    />
                    <small id="helpId" class="form-text text-muted">Nome da categoria</small>
                </div>
                <button
                        type="submit"
                        class="btn btn-primary"
                    >
                    Alterar
                    </button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
