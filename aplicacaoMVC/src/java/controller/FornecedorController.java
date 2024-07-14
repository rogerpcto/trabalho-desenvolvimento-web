package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FornecedorDAO;
import entidade.Cliente;
import entidade.Fornecedor;
    
@WebServlet(name = "CadastrarFornecedor", urlPatterns = {"/cadastrarFornecedor"})
public class FornecedorController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/fornecedor/cadastrarFornecedor.jsp");
        rd.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String razao_social = request.getParameter("razao_social");
        String cnpj = request.getParameter("cnpj");
        //formatar cnpj de forma correta
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        try {
            Fornecedor fornecedor = new Fornecedor(razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email);
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.Inserir(fornecedor);
        }catch (Exception e) {}
         response.sendRedirect("comprador/home");
    }      
}