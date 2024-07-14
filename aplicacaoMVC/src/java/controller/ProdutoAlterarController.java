
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
/**
 *
 * @author aluno
 */
@WebServlet(name = "ProdutoAlterarController", urlPatterns = {"/comprador/alterarProduto"})
public class ProdutoAlterarController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            request.setAttribute("produto", produto);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/produto/alterarProdutos.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        String nome_produto = request.getParameter("nome_produto");
        String descricao = request.getParameter("descricao");
        int id_categoria = Integer.parseInt(request.getParameter("categoria"));
        float preco_compra = Float.parseFloat(request.getParameter("preco_compra"));
        float preco_venda = Float.parseFloat(request.getParameter("preco_venda"));
        int quantidade_disponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
        String liberado_venda = request.getParameter("liberado_venda");
        if (liberado_venda != null) {
            liberado_venda = "S";
        } else {
        liberado_venda = "N";
        }
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Categoria categoria = categoriaDAO.getCategoria(id_categoria);
            Produto produto = new Produto(id_produto, nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, categoria);
            ProdutoDAO produtodao = new ProdutoDAO();
            produtodao.Alterar(produto);
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/comprador/listarProdutos");
    }
}

