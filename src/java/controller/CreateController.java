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
import products.CategoryDTO;
import products.ProductDAO;
import products.ProductDTO;
import products.ProductErrorDTO;

/**
 *
 * @author SnoweyMTT
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "createProduct.jsp";
    private static final String SUCCESS = "vegetables.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productName = request.getParameter("productName");
            String productImage = request.getParameter("productImage");
            String categoryName = request.getParameter("categoryName");
            String quantity = request.getParameter("quantity");
            String price = request.getParameter("price");
            CategoryDTO category = new CategoryDTO();

            boolean flag = true;

            ProductDAO dao = new ProductDAO();

            ArrayList<CategoryDTO> clist = dao.getCategories();
            request.setAttribute("LIST_CATEGORY", clist);
            ProductErrorDTO error = new ProductErrorDTO();

            if (productName.isEmpty() || productName.length()<2 || productName.length()>40) {
                flag = false;
                error.setProductNameError("Product Name must be in range [2,40] and with no symbol");
            }

            if (categoryName.isEmpty()) {
                flag = false;
                error.setCategoryNameError("Category Name must be selected");
            } else {
                for (CategoryDTO cat : clist) {
                    if (categoryName.equals(cat.getCategoryName())) {
                        category = cat;
                        break;
                    }
                }
            }

            if (productImage.isEmpty()) {
                flag = false;
                error.setProductImageError("Please select Product Image");
            }
            
            if (!productImage.endsWith("jpeg") || !productImage.endsWith("jpg") || !productImage.endsWith("png")) {
                flag = false;
                error.setProductImageError("Please select file ends with .jpeg, .jpg, or .png");
            }

            if (quantity.isEmpty() || !quantity.matches("^[0-9]{0,3}$")) {
                flag = false;
                error.setQuantityError("Quantity must be in range [0,999]");
            }

            if (flag) {
                Date createDate = new Date(System.currentTimeMillis());
                boolean checkInsert = dao.insertProduct(new ProductDTO(productName, category, true, Integer.parseInt(quantity), new BigDecimal(price), createDate, productImage));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at CreateController:" + e.toString());
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
