package controller;

import entidade.Categoria;
import entidade.Cliente;
import entidade.Compra;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoriaDAO;
import model.ClienteDAO;
import model.CompraDAO;
    
@WebServlet(name = "CategoriaAlterarController", urlPatterns = {"/categoria/alterar"})
public class CategoriaAlterarController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            Categoria categoria = categoriaDAO.getCategoria(id);
            request.setAttribute("categoria", categoria);
        }catch (Exception e) {}
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/categoria/alterarCategoria.jsp");
        rd.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome_categoria = request.getParameter("nome_categoria");
        try {
            Categoria categoria = new Categoria(nome_categoria);
            categoria.setId(id);
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.Alterar(categoria);
        }catch (Exception e) {}
         response.sendRedirect("/aplicacaoMVC/listar/categorias");
    }      
}