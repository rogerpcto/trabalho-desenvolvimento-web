package controller;

import entidade.Categoria;
import entidade.Cliente;
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
    
@WebServlet(name = "CadastrarCategoria", urlPatterns = {"/cadastrarCategoria"})
public class CategoriaController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/categoria/cadastrarCategoria.jsp");
        rd.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String nome_categoria = request.getParameter("nome_categoria");
        try {
            Categoria categoria = new Categoria(nome_categoria);
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.Inserir(categoria);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/categoria/cadastrarCategoria.jsp");
        rd.forward(request, response);
    }      
}