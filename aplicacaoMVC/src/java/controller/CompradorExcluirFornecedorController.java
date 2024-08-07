
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Compra;
import entidade.Fornecedor;
import entidade.Produto;
import entidade.Venda;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoriaDAO;
import model.CompraDAO;
import model.FornecedorDAO;
import model.ProdutoDAO;
import model.VendaDAO;
/**
 *
 * @author aluno
 */
@WebServlet(name = "CompradorExcluirFornecedorController", urlPatterns = {"/comprador/excluirFornecedor"})
public class CompradorExcluirFornecedorController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = fornecedorDAO.getFornecedor(id_fornecedor);
            CompraDAO compraDAO = new CompraDAO();
            Compra compra = compraDAO.getPrimeiraCompraFornecedor(id_fornecedor);
            if (compra.getProduto() == null){
                fornecedorDAO.Excluir(fornecedor);
            }
            else{
                request.setAttribute("mensagem", "Não foi possível excluir o Fornecedor, pois existem compras associadas a ele.");
                request.getRequestDispatcher("views/funcionario/homeComprador.jsp").forward(request, response);
            }
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/comprador/listarFornecedor");
    }
}

