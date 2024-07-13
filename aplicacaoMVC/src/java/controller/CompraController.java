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
import model.CompraDAO;
import model.FornecedorDAO;
import model.FuncionarioDAO;
import model.VendaDAO;
import entidade.Fornecedor;
import entidade.Funcionario;
import entidade.Produto;
import entidade.Compra;
/**
 *
 * @author Logan
 */
@WebServlet(name = "CompraController", urlPatterns = {"/ComprasController"})
public class CompraController extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/compra/cadastrarCompra.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        int id_funcionario = Integer.parseInt(request.getParameter("funcionario"));
        int id_produto = Integer.parseInt(request.getParameter("produto"));
        int id_fornecedor = Integer.parseInt(request.getParameter("fornecedor"));
        int valor_compra = Integer.parseInt(request.getParameter("valor_compra"));
        int quantidade_compra = Integer.parseInt(request.getParameter("quantidade_compra"));
        String data_string = request.getParameter("data_compra");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date utilDate = sdf.parse(data_string);
            java.sql.Date data = new java.sql.Date(utilDate.getTime());
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = produtoDAO.getProduto(id_produto);
            FornecedorDAO FornecedorDAO = new FornecedorDAO();
            Fornecedor fornecedor = FornecedorDAO.getFornecedor(id_fornecedor);
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.getFuncionario(id_funcionario);
            Compra compra = new Compra(quantidade_compra, data, valor_compra, fornecedor, produto, funcionario);
            CompraDAO compraDAO = new CompraDAO();
            compraDAO.Inserir(compra);
        }catch (Exception e) {}
        rd = request.getRequestDispatcher("/views/compra/cadastrarCompra.jsp");
        rd.forward(request, response);
    }
}
