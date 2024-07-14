package controller;

import entidade.Funcionario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FuncionarioDAO;
/**
 *
 * @author aluno
 */
@WebServlet(name = "FuncionarioAlterarController", urlPatterns = {"/funcionario/alterar"})
public class FuncionarioAlterarController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.getFuncionario(id);
            request.setAttribute("funcionario", funcionario);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/funcionario/alterarFuncionario.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher rd;
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        int numero_papel = Integer.parseInt(request.getParameter("papel"));
        try {
            Funcionario funcionario = new Funcionario(id, nome, cpf, papel, senha, email);
            FuncionarioDAO funcionariodao = new FuncionarioDAO();
            funcionariodao.Alterar(funcionario);
        }catch (Exception e) {}
        response.sendRedirect("home");
    }
}

