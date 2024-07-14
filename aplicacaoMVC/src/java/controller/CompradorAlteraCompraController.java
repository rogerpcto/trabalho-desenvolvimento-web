package controller;

import entidade.Categoria;
import entidade.Compra;
import entidade.Fornecedor;
import entidade.Funcionario;
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
import model.CompraDAO;
import model.FornecedorDAO;
import model.FuncionarioDAO;
import model.ProdutoDAO;

@WebServlet(name = "CompradorAlteraCompraController", urlPatterns = {"/comprador/alterarCompra"})
public class CompradorAlteraCompraController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_compra = Integer.parseInt(request.getParameter("id_compra"));
        try {
            CompraDAO compraDAO = new CompraDAO();
            Compra compra = compraDAO.getCompra(id_compra);
            request.setAttribute("compra", compra);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/compra/alterarCompra.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id  = Integer.parseInt(request.getParameter("id_compra"));
        int valor_compra = Integer.parseInt(request.getParameter("valor_compra"));
        int quantidade_compra = Integer.parseInt(request.getParameter("quantidade_compra"));
        String data_compra = request.getParameter("data_compra");
        java.sql.Date data = java.sql.Date.valueOf(data_compra);
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = fornecedorDAO.getFornecedor(id_fornecedor);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            HttpSession session = request.getSession();
            Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");
            Compra compra = new Compra(id, quantidade_compra, data, valor_compra,fornecedor,produto,funcionario);
            CompraDAO CompraDAO = new CompraDAO();
            CompraDAO.Alterar(compra);
        }catch (Exception e) {}
         response.sendRedirect("/aplicacaoMVC/comprador/listarCompras");
    } 
}


