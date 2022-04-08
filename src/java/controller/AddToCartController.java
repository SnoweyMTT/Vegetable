/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import products.CategoryDTO;
import products.ProductDAO;
import products.ProductDTO;
import products.ShoppingCartItemDTO;
import user.UserDTO;

/**
 *
 * @author SnoweyMTT
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "loginPage.jsp";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user != null) {
                ArrayList<ShoppingCartItemDTO> plistInCart = (ArrayList<ShoppingCartItemDTO>) session.getAttribute("plistInCart");

                if (plistInCart == null) {
                    plistInCart = new ArrayList<>();
                }

                //Lay san pham trong CSDL
                String productID = request.getParameter("productID");
                String quantityBuy = request.getParameter("quantity");
                ProductDAO dao = new ProductDAO();
                ProductDTO product = dao.getProductsByID(productID);

                //Lay san pham trong gio hang
                ShoppingCartItemDTO productCart = new ShoppingCartItemDTO();

                for (ShoppingCartItemDTO pc : plistInCart) {
                    if (pc.getProduct().getProductID() == product.getProductID()) {
                        productCart = pc;
                        productCart.setQuantity(productCart.getQuantity() + Integer.parseInt(quantityBuy));
                        productCart.getProduct().setQuantity(product.getQuantity() - Integer.parseInt(quantityBuy));
                        break;
                    }
                }

                if (productCart.getProduct() == null) {
                    int productId = product.getProductID();
                    String productName = product.getProductName();
                    boolean status = product.isStatus();
                    int quantity = product.getQuantity() - Integer.parseInt(quantityBuy);
                    if (quantity == 0){
                        status = false;
                    }
                    BigDecimal price = product.getPrice();
                    Date createDate = product.getCreateDate();
                    Date updateDate = product.getUpdateDate();
                    String productImage = product.getProductImage();
                    CategoryDTO category = product.getCategory();

                    productCart.setProduct(new ProductDTO(productId, productName, category, status, quantity, price, createDate, updateDate, productImage));
                    productCart.setQuantity(Integer.parseInt(quantityBuy));
                    plistInCart.add(productCart);
                }

                boolean check = dao.updateProductQuantity(productCart.getProduct());

                if (check) {
                    session.setAttribute("plistInCart", plistInCart);
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at AddToCartController:" + e.toString());
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
