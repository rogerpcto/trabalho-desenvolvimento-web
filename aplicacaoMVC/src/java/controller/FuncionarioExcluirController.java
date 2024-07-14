
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entidade.Compra;
import entidade.Funcionario;
import entidade.Venda;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CompraDAO;
import model.FuncionarioDAO;
import model.VendaDAO;
/**
 *
 * @author aluno
 */
@WebServlet(name = "FuncionarioExcluirController", urlPatterns = {"/funcionario/excluir"})
public class FuncionarioExcluirController extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.getFuncionario(id);
            VendaDAO vendaDAO = new VendaDAO();
            Venda venda = vendaDAO.getPrimeiraVendaFuncionario(id);
            CompraDAO compraDAO = new CompraDAO();
            Compra compra = compraDAO.getPrimeiraCompraFuncionario(id);
            if (compra.getProduto() == null && venda.getProduto() == null){
                funcionarioDAO.Excluir(funcionario);
            }
            else{
                request.setAttribute("mensagem", "Não foi possível excluir o Funcionário, pois existem vendas e/ou compras associadas a ele.");
                request.getRequestDispatcher("views/administrador/home.jsp").forward(request, response);
            }
        }catch (Exception e) {}
        response.sendRedirect("/aplicacaoMVC/administrador/home");
    }
}

