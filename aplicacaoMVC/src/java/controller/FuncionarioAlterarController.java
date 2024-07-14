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
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        String nome_produto = request.getParameter("nome_produto");
        String descricao = request.getParameter("descricao");
        int id_categoria = Integer.parseInt(request.getParameter("categoria"));
        float preco_compra = Float.parseFloat(request.getParameter("preco_compra"));
        float preco_venda = Float.parseFloat(request.getParameter("preco_venda"));
        int quantidade_disponivel = Integer.parseInt(request.getParameter("quantidade_disponivel"));
        String liberado_venda = request.getParameter("liberado_venda");
        if (liberado_venda != null) {
            liberado_venda = "S";
        } else {
        liberado_venda = "N";
        }
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            Categoria categoria = categoriaDAO.getCategoria(id_categoria);
            Produto produto = new Produto(id_produto, nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, categoria);
            ProdutoDAO produtodao = new ProdutoDAO();
            produtodao.Alterar(produto);
        }catch (Exception e) {}
        response.sendRedirect("home");
    }
}

