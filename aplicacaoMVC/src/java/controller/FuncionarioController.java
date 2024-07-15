package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FuncionarioDAO;
import entidade.Cliente;
import entidade.Funcionario;
    
@WebServlet(name = "CadastrarFuncionario", urlPatterns = {"/CadastrarFuncionario"})
public class FuncionarioController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/funcionario/cadastrarFuncionario.jsp");
        rd.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        int numero_papel = Integer.parseInt(request.getParameter("papel"));
        try {
            //papel ta como varchar(1) no Banco de Dados, como faremos?
            Funcionario funcionario = new Funcionario(nome, cpf, numero_papel, senha, email);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.Inserir(funcionario);
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/funcionarios/listar");
    }      
}    