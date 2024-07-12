/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProdutoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ClienteDAO;
import model.FuncionarioDAO;
import model.VendaDAO;
import entidade.Cliente;
import entidade.Funcionario;
import entidade.Produto;
import entidade.Venda;
/**
 *
 * @author Logan
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendasController"})
public class VendaController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        int id_produto = Integer.parseInt(request.getParameter("id"));
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            request.setAttribute("produto", produto);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/venda/cadastrarVenda.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_funcionario = Integer.parseInt(request.getParameter("funcionario"));
        int id_produto = Integer.parseInt(request.getParameter("produto"));
        int id_cliente = Integer.parseInt(request.getParameter("cliente"));
        float valor_venda = Float.parseFloat(request.getParameter("valor_venda"));
        int quantidade_venda = Integer.parseInt(request.getParameter("quantidade_venda"));
        String data_string = request.getParameter("data_venda");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date data = sdf.parse(data_string);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getCliente(id_cliente);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.getFuncionario(id_funcionario);
            Venda venda = new Venda(quantidade_venda, data, valor_venda, cliente, produto, funcionario);
            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.Inserir(venda);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/venda/cadastrarVenda.jsp");
        rd.forward(request, response);
    }
}
