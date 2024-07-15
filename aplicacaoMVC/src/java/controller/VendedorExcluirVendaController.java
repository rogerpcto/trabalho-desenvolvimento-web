
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Categoria;
import entidade.Cliente;
import entidade.Compra;
import entidade.Fornecedor;
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
import model.CompraDAO;
import model.FornecedorDAO;
import model.ProdutoDAO;
import model.VendaDAO;
/**
 *
 * @author aluno
 */
@WebServlet(name = "VendedorExcluirVendaController", urlPatterns = {"/vendedor/excluirVenda"})
public class VendedorExcluirVendaController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = vendaDAO.getVenda(id);
            vendaDAO.Excluir(venda);
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/vendedor/listarVendas");
    }
}

