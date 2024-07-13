<%@page import="entidade.Funcionario"%>
<%@page import="model.FuncionarioDAO"%>
<%@page import="entidade.Fornecedor"%>
<%@page import="model.FornecedorDAO"%>
<%@page import="java.util.List"%>
<%@page import="entidade.Produto"%>
<%@page import="model.ProdutoDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script>
    function calcularValorcompra() {
        const precocompra = parseFloat(document.getElementById('preco_compra').value);
        const quantidadecompraInput = document.getElementById('quantidade_compra');
        let quantidadecompra = quantidadecompraInput.value.trim(); // Remover espaços em branco

        // Verificar se o campo está vazio
        if (quantidadecompra === '') {
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return; // Sai da função se estiver vazio
        }

        // Verificar se a quantidade é um número válido
        quantidadecompra = parseInt(quantidadecompra);
        if (isNaN(quantidadecompra) || quantidadecompra <= 0) {
            alert("Digite uma quantidade válida.");
            quantidadecompraInput.value = ''; // Limpar campo de quantidade
            document.getElementById('valor_compra').value = ''; // Limpar valor da compra
            return;
        }

        const valorcompra = precocompra * quantidadecompra;
        document.getElementById('valor_compra').value = parseInt(valorcompra);
    }
    </script>
</head>
<body>
    <div class="container">
        <div class="col-sm-6 mt-5 mb-5">
            <form action="ComprasController" method="post">
                <div class="mb-3">
                    <label for="Produto" class="form-label">Produto</label>
                    <select class="form-select" name="id_produto" id="Produto" required aria-describedby="helpId">
                        <% 
                                ProdutoDAO produtoDAO = new ProdutoDAO();
                                List<Produto> produtos = produtoDAO.ListaDeProdutos();
                                for (Produto produto : produtos) {
                        %>
                        <option value="<%= produto.getId() %>" data-preco="<%= produto.getPrecoCompra() %>">
                            <%= produto.getNomeProduto() %>
                        </option>
                        <% 
                            }
                        %>
                    </select>
                    <input type="hidden" id="preco_compra" name="preco_compra" value="">
                    <small id="helpId" class="form-text text-muted">Produto</small>
                </div>
                <div class="mb-3">
                    <label for="quantidade_compra" class="form-label">Quantidade</label>
                    <input required
                           maxlength="5" minlength="1"
                           type="number"
                           class="form-control"
                           name="quantidade_compra"
                           id="quantidade_compra"
                           aria-describedby="helpId"
                           oninput="calcularValorcompra()"
                    />
                    <small id="helpId" class="form-text text-muted"></small>
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
                           readonly
                    />
                    <small id="helpId" class="form-text text-muted">Valor da compra</small>
                </div>
                <div class="mb-3">
                    <label for="data_compra" class="form-label">Data</label>
                    <input required
                           maxlength="10" minlength="10"
                           type="date"
                           class="form-control"
                           name="data_compra"
                           id="data_compra"
                           aria-describedby="helpId"
                    />
                    <small id="helpId" class="form-text text-muted">Data da compra</small>
                </div>
                <div class="mb-3">
                    <label for="Fornecedor" class="form-label">Fornecedor</label>
                    <select class="form-select" name="id_fornecedor" id="id_fornecedor" required aria-describedby="helpId">
                        <% 
                            try {
                                FornecedorDAO fornecedorDAO = new FornecedorDAO();
                                List<Fornecedor> fornecedores = fornecedorDAO.ListaDeFornecedores();
                                for (Fornecedor fornecedor : fornecedores) {
                        %>
                        <option value="<%= fornecedor.getId() %>">
                            <%= fornecedor.getRazaoSocial() %>
                        </option>
                        <% 
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Fornecedor</small>
                </div>
                <div class="mb-3">
                    <label for="Funcionario" class="form-label">Funcionário</label>
                    <select class="form-select" name="id_funcionario" id="id_funcionario" required aria-describedby="helpId">
                        <% 
                            try {
                                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                                List<Funcionario> funcionarios = funcionarioDAO.ListaDeVendedores();
                                for (Funcionario funcionario : funcionarios) {
                        %>
                        <option value="<%= funcionario.getId() %>">
                            <%= funcionario.getNome() %>
                        </option>
                        <% 
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </select>
                    <small id="helpId" class="form-text text-muted">Funcionário</small>
                </div>
                <button type="submit" class="btn btn-primary">Comprar</button>
            </form>
        </div>
    </div>
    <script>
        // Script para atualizar o valor do preço de compra quando o produto é selecionado
        document.getElementById('Produto').addEventListener('change', function() {
            const selectedOption = this.options[this.selectedIndex];
            const precoCompra = selectedOption.getAttribute('data-preco');
            document.getElementById('preco_compra').value = precoCompra;
            calcularValorcompra();
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
