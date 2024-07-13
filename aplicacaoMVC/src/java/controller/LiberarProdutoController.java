/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CategoriaDAO;
import model.ProdutoDAO;

@WebServlet(name = "LiberarProdutoController", urlPatterns = {"/Produtos/Liberar"})
public class LiberarProdutoController extends HttpServlet {    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        // pegando os parâmetros do request
        int quantidade_disponivel = Integer.parseInt(request.getParameter("id"));
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {

            Produto produto = produtoDAO.getProduto(id);
            String liberadoVenda = produto.getLiberadoVenda();
            liberadoVenda = liberadoVenda == "S" ? "N" : "S";
            produto.setLiberadoVenda(liberadoVenda);
            produtoDAO.Alterar(produto);
        }catch (Exception e) {}
        
        //levar usuario para pagina com todos os produtos
        rd = request.getRequestDispatcher("/views/produto/produtos.jsp");
        rd.forward(request, response);
        
    }
}
