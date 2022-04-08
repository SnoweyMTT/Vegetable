/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import products.ProductDAO;
import products.ProductDTO;
import products.ShoppingCartItemDTO;

/**
 *
 * @author SnoweyMTT
 */
@WebServlet(name = "EditCartController", urlPatterns = {"/EditCartController"})
public class EditCartController extends HttpServlet {

    private static final String ERROR = "ViewCartController";
    private static final String SUCCESS = "ViewCartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int count = Integer.parseInt(request.getParameter("count"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            HttpSession session = request.getSession();
            ArrayList<ShoppingCartItemDTO> plistInCart = (ArrayList<ShoppingCartItemDTO>) session.getAttribute("plistInCart");

            if (plistInCart != null) {
                ShoppingCartItemDTO cart = plistInCart.get(count - 1);
                if (quantity != cart.getQuantity()) {
                    ProductDAO dao = new ProductDAO();
                    int number = 0;
                    if (quantity > cart.getQuantity()) {
                        number = quantity - cart.getQuantity();
                        cart.getProduct().setQuantity(cart.getProduct().getQuantity() - number);
                    } else {
                        number = cart.getQuantity() - quantity;
                        cart.getProduct().setQuantity(cart.getProduct().getQuantity() + number);
                    }
                    boolean check = dao.updateProductQuantity(cart.getProduct());

                    if (check) {
                        cart.setQuantity(quantity);
                        cart.setProduct(cart.getProduct());
                    }
                }
            }
            session.setAttribute("plistInCart", plistInCart);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at EditCartController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
