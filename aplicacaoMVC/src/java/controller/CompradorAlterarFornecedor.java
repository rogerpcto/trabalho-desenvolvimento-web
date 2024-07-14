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

@WebServlet(name = "CompradorAlterarFornecedor", urlPatterns = {"/comprador/alterarFornecedor"})
public class CompradorAlterarFornecedor extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = fornecedorDAO.getFornecedor(id_fornecedor);
            request.setAttribute("fornecedor", fornecedor);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/fornecedor/alterarFornecedor.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id_fornecedor = Integer.parseInt(request.getParameter("id_fornecedor"));
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        try {
            Fornecedor fornecedor = new Fornecedor(id_fornecedor, razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email);
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.Alterar(fornecedor);
        }catch (Exception e) {}
         response.sendRedirect("/aplicacaoMVC/comprador/listarFornecedor");
    } 
}


