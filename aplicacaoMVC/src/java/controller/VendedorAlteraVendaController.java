package controller;

import entidade.Categoria;
import entidade.Cliente;
import entidade.Fornecedor;
import entidade.Funcionario;
import entidade.Produto;
import entidade.Venda;
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
import model.FuncionarioDAO;
import model.ProdutoDAO;
import model.VendaDAO;

@WebServlet(name = "VendedorAlteraVendaController", urlPatterns = {"/vendedor/alterarVenda"})
public class VendedorAlteraVendaController extends HttpServlet {
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = vendaDAO.getVenda(id);
            request.setAttribute("venda", venda);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/venda/alterarVenda.jsp");
        rd.forward(request, response);

    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id_venda"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        String data_string = request.getParameter("data_venda");
        try {
            java.sql.Date data = java.sql.Date.valueOf(data_string);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getCliente(id_cliente);
            HttpSession session = request.getSession();
            Funcionario funcionario = (Funcionario) session.getAttribute("funcionario");
            Venda venda = new Venda(quantidade_venda, data, valor_venda, cliente, produto, funcionario);
            venda.setId(id);
            VendaDAO vendaDAO = new VendaDAO();
            int quantidadeAntiga = vendaDAO.getVenda(id).getQuantidade();
            produto.atualizaQuantidade(quantidadeAntiga-quantidade_venda);
            vendaDAO.Alterar(venda);
            produtoDAO.Alterar(produto);
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/vendedor/listarVendas");
    } 
}


