package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;


/**
 *
 * @author hp
 */
@WebServlet(name = "AdmRelatorioTotalVendasController", urlPatterns = {"/administrador/relatorioTotalVendas"})
public class AdmRelatorioTotalVendasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String dataParam = request.getParameter("data");
        Date data = null;

        if (dataParam != null && !dataParam.trim().isEmpty()) {
            data = Date.valueOf(dataParam);
        }

        request.setAttribute("data", data);
        
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/administrador/relatorioTotalVendas.jsp");
        rd.forward(request, response);
    }
}
