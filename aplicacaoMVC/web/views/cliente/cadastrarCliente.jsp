<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="<%= request.getContextPath() %>/yourServlet" method="post">
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
                    <small id="helpId" class="form-text text-muted">Nome do Cliente</small>
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
                    <small id="helpId" class="form-text text-muted">CPF do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="endereco" class="form-label">Endereço</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="endereco"
                        id="endereco"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Endereço do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="bairro" class="form-label">Bairro</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="bairro"
                        id="bairro"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Bairro do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="cidade" class="form-label">Cidade</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="cidade"
                        id="cidade"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Cidade do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="uf" class="form-label">UF</label>
                    <input required
                        maxlength="2" minlength="2"
                        type="text"
                        class="form-control"
                        name="uf"
                        id="uf"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">UF do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="cep" class="form-label">CEP</label>
                    <input required
                        maxlength="9" minlength="3"
                        type="text"
                        class="form-control"
                        name="cep"
                        id="cep"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">CEP do Cliente</small>
                </div>
                <div class="mb-3">
                    <label for="telefone" class="form-label">Telefone</label>
                    <input required
                        maxlength="20"
                        type="text"
                        class="form-control"
                        name="telefone"
                        id="telefone"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Telefone do Cliente</small>
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
                    <small id="helpId" class="form-text text-muted">E-mail do Cliente</small>
                </div>
                <button
                        type="submit"
                        class="btn btn-primary"
                    >
                    Entrar
                    </button>
            </form>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>
