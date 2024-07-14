package controller;

import entidade.Categoria;
import entidade.Cliente;
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
import model.ClienteDAO;
import model.FornecedorDAO;
import model.ProdutoDAO;

@WebServlet(name = "VendedorAlterarClienteController", urlPatterns = {"/vendedor/alterarCliente"})
public class VendedorAlterarClienteController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getCliente(id_cliente);
            request.setAttribute("cliente", cliente);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/cliente/alterarCliente.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id"));
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
            cliente.setId(id);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.Alterar(cliente);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/cliente/listarClientes.jsp");
        rd.forward(request, response);
    } 
}