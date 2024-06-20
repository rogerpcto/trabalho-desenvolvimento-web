<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="<%= request.getContextPath() %>/yourServlet" method="post">
                <div class="mb-3">
                    <label for="quantidade_compra" class="form-label">Quantidade</label>
                    <input required
                        maxlength="5" minlength="1"
                        type="text"
                        class="form-control"
                        name="quantidade_compra"
                        id="quantidade_compra"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Quantidade da Compra</small>
                </div>
                <div class="mb-3">
                    <label for="data_compra" class="form-label">Data</label>
                    <input required
                        maxlength="10" minlength="10"
                        type="text"
                        class="form-control"
                        name="data_compra"
                        id="data_compra"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Data da Compra</small>
                </div>
                <div class="mb-3">
                    <label for="valor_compra" class="form-label">Valor</label>
                    <input required
                        maxlength="50" minlength="3"
                        type="text"
                        class="form-control"
                        name="valor_compra"
                        id="valor_compra"
                        aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Valor da Compra</small>
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
