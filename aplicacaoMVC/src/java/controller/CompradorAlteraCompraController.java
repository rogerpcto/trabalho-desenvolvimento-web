package controller;

import entidade.Categoria;
import entidade.Fornecedor;
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
import model.FornecedorDAO;
import model.ProdutoDAO;

@WebServlet(name = "CompradorAlterarCompraController", urlPatterns = {"/comprador/alterarCompra"})
public class CompradorAlterarCompraController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = fornecedorDAO.getFornecedor(id_fornecedor);
            request.setAttribute("fornecedor", fornecedor);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/fornecedor/alterarCompra.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        int id_comprador = request.getParameter("id_comprador");
        int id_produto = request.getParameter("id_produto");
        int id  = request.getParameter("id");
        float valor_compra = request.getParameter("valor_compra");
        int quantidade_compra = request.getParameter("quantidade_compra");
        Date data_compra = request.getParameter("data_compra");
        try {
            Compra compra = new Compra(id, quantidade_compra,data_compra, valor_compra,id_fornecedor,id_produto,id_comprador);
            CompraDAO CompraDAO = new CompraDAO();
            CompraDAO.Alterar(fornecedor);
        }catch (Exception e) {}
         response.sendRedirect("/aplicacaoMVC/comprador/listarCompras");
    } 
}


