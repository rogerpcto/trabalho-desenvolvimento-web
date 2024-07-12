/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Cliente;
import entidade.Funcionario;
import entidade.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;
import model.ProdutoDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.ClienteDAO;
import model.FuncionarioDAO;
/**
 *
 * @author Logan
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendasController"})
public class VendaController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
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
        } catch (ParseException e) {
        }
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ClienteDAO clienteDAO = new ClienteDAO();
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            ArrayList<Produto> produto = produtoDAO.ListaDeProdutos();
            ArrayList<Funcionario> funcionario = funcionarioDAO.ListaDeVendedores();
            ArrayList<Cliente> clientes = clienteDAO.ListaDeClientes();
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/venda/cadastrarVenda.jsp");
        rd.forward(request, response);
    }
}
