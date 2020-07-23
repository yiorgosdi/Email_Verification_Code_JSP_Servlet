package com.javaemail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yiorgos
 */
public class UserVerify extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String name = request.getParameter("username"); 
            String email = request.getParameter("useremail"); 
            
            SendEmail se = new SendEmail(); 
            String code = se.getRandom(); // here is the code to verify 
            
            User user = new User(name, email, code); // here is the code for verification  
                        
            boolean testEmail = se.sendEmail(user); 
                    
            if(testEmail){
                HttpSession session = request.getSession(); 
                session.setAttribute("authcode", user); 
                response.sendRedirect("Verify.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AddressException ex) {
            Logger.getLogger(UserVerify.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
