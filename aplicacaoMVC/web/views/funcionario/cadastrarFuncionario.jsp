<%@page import="entidade.Papel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Funcionário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="col-sm-6 mt-5 mb-5">
            <form action = "CadastrarFuncionario" method="post">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="nome"
                        id="nome"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Nome do Funcionário</small>
                </div>
                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input required
                        maxlength="14" minlength="14"
                        type="text"
                        class="form-control"
                        name="cpf"
                        id="cpf"
                        placeholder="999.999.999-99"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">CPF do Funcionário</small>
                </div>
                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input required
                        maxlength="10" minlength="4"
                        type="password"
                        class="form-control"
                        name="senha"
                        id="senha"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Senha do Funcionário</small>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">E-mail</label>
                    <input required
                        maxlength="50"
                        type="text"
                        class="form-control"
                        name="email"
                        id="email"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">E-mail do Funcionário</small>
                </div>
                <div class="mb-3">
                    <label for="papel" class="form-label">Papel</label>
                    <select required class="form-select" name="papel" id="papel" aria-describedby="helpId">
                        <%
                            for (Papel papel : Papel.values()) {
                        %>
                            <option value="<%= papel.ordinal() %>"><%= papel.toString() %></option>
                        <%
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Papel do Funcionário</small>
                </div>
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
