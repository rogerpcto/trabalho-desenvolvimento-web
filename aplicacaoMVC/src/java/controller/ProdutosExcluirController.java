
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Compra;
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
import model.ProdutoDAO;
import model.VendaDAO;
/**
 *
 * @author aluno
 */
@WebServlet(name = "ProdutosExcluirController", urlPatterns = {"/comprador/excluirProduto"})
public class ProdutosExcluirController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = vendaDAO.getPrimeiraVendaProduto(id_produto);
            CompraDAO compraDAO = new CompraDAO();
            Compra compra = compraDAO.getPrimeiraCompraProduto(id_produto);
            if (compra.getProduto() == null && venda.getProduto() == null){
                produtoDAO.Excluir(produto);
            }
            else{
                request.setAttribute("mensagem", "Não foi possível excluir o Produto, pois existem vendas e/ou compras associadas a ele.");
                request.getRequestDispatcher("views/funcionario/homeComprador.jsp").forward(request, response);
            }
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/comprador/listarProdutos");
    }
}

