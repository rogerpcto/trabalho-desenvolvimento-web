
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
@WebServlet(name = "VendedorExcluirClienteController", urlPatterns = {"/vendedor/excluirCliente"})
public class VendedorExcluirClienteController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.getCliente(id);
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = vendaDAO.getPrimeiraVendaCliente(id);
            if (venda.getCliente() == null){
                clienteDAO.Excluir(cliente);
            }
            else{
                request.setAttribute("mensagem", "Não foi possível excluir o Cliente, pois existem compras associadas a ele.");
                request.getRequestDispatcher("views/funcionario/homeVendedor.jsp").forward(request, response);
            }
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/vendedor/listarClientes");
    }
}

