
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Compra;
import entidade.Funcionario;
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
@WebServlet(name = "CategoriaExcluirController", urlPatterns = {"/categoria/excluir"})
public class CategoriaExcluirController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = categoriaDAO.getCategoria(id);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getPrimeiroProdutoCategoria(id);
            if (produto.getCategoria() == null){
                categoriaDAO.Excluir(categoria);
            }
            else{
                request.setAttribute("mensagem", "Não foi possível excluir a Categoria, pois existem vendas e/ou compras associadas a ele.");
                request.getRequestDispatcher("views/administrador/home.jsp").forward(request, response);
            }
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/listar/categorias");
    }
}

