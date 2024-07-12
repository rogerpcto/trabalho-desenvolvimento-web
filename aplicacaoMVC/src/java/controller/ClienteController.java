package controller;

import entidade.Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ClienteDAO;
    
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class ClienteController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/cliente/cadastrarCliente.jsp");
        rd.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String uf = request.getParameter("uf");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        try {
            Cliente cliente = new Cliente(nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.Inserir(cliente);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/cliente/cadastrarCliente.jsp");
        rd.forward(request, response);
    }      
}