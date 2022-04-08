/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author SnoweyMTT
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    private static final String ERROR = "loginPage.jsp";
    private static final String SUCCESS = "loginPage.html";
    

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError("", "", "", "", "");
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String email = request.getParameter("email");
            String roleID = "US";
            
            boolean flag = true;
            
            if(userID.isEmpty() || userID.matches("^[a-zA-Z0-9]{2,30}$")){
                flag = false;
                error.setUserIDError("User Id must be in range [2,30] and with no symbol");
            }
            
            if(fullName.isEmpty() || fullName.matches("^[a-zA-Z0-9]{5,30}$")){
                flag = false;
                error.setFullNameError("Full Name must be in range [5,30] and with no symbol");
            }
            
            if(password.isEmpty() || password.length()<3 || password.length()>50){
                flag = false;
                error.setUserIDError("Password must be in range [3,50]");
            }
            
            if(email.isEmpty() || !email.matches("^[a-zA-Z0-9_]{0,30}+@[a-zA-Z0-9.-]{0,10}.[a-zA-Z]{2,6}$")){
                flag = false;
                error.setUserEmailError("Please input your email");
            }
        
            if(!password.equals(confirm)){
                flag = false;
                error.setConfirmPasswordError("RePassword is not the same");
            }
            
            if(flag){
                UserDAO dao = new UserDAO();
                UserDTO user = new UserDTO(userID, fullName, roleID, password, "", email, "", "");
                dao.insertUser(user);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR_SIGN", error);
            }        
        }catch(Exception e){
            log("Error at SignUpController: " + e.toString());
        }finally{
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
