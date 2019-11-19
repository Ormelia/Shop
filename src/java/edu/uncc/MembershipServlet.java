package edu.uncc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import business.Product;
import business.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.CookieUtil;

/**
 *
 * @author RTyle
 */
//@WebServlet(urlPatterns = {"/membership"})
public class MembershipServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MembershipServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MembershipServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        User user = new User();
        HttpSession session = request.getSession();
        
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/users.txt");
        String action = request.getParameter("action");
        String url = "";
        
        if (action.equals("logout")) {
            Cookie[] cookies = request.getCookies();
            System.out.println("in the log out/delete cookies else if.");
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); //delete the cookie
                cookie.setPath("/"); //allow the download application to access it
                response.addCookie(cookie);
            }
           
            session.invalidate();
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            
        }
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

        
        
        User user = new User();
        HttpSession session = request.getSession();
        
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/users.txt");
        String action = request.getParameter("action");
        String url = "";
        if (action.equals("signup")) {
            //url = "/login.jsp";
            
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if (!firstname.isEmpty() && !firstname.equals("") &&
                    !lastname.isEmpty() && !lastname.equals("") &&
                    !email.isEmpty() && !email.equals("") &&
                    !password.isEmpty() && !password.equals("") &&
                    email.contains("@")) {
                
                user = new User(firstname, lastname, email, password);
                
                Cookie c1 = new Cookie("emailCookie", email);
                c1.setMaxAge(60 * 5);
                response.addCookie(c1);
                
                session.setAttribute("user", user);
                session.setAttribute("userPass", user.getPassword());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("emailCookie", c1);
                
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (action.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if (password.equals((String)session.getAttribute("userPass")) && email.equals((String)session.getAttribute("userEmail"))) {
                
                Cookie c1 = new Cookie("emailCookie", email);
                response.addCookie(c1);
                c1.setMaxAge(60 * 5); // lasts five minutes only. 
                session.setAttribute("user", user);
                session.setAttribute("emailCookie", c1);
                
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
            } else {
                System.out.println("Not in Database");
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }  else if (action == null) {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            try (PrintWriter out = response.getWriter()) {
                out.println("error: no action");
            }
            
        }
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
//
//            // if cookie doesn't exist, go to Registration page/sign up page. else, find their email and send the user to the session object. [
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/signup.jsp";
                getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
            }
            
    }

   // processRequest(request, response);
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