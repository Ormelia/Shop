package edu.uncc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import business.Product;
import business.User;
import util.CookieUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author jmarq
 */
//@WebServlet(urlPatterns = {"/productManagement"})
public class ProductManagementServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductManagementServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
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
        String path = getServletContext().getRealPath("/WEB_INF/products.txt");
        HttpSession session = request.getSession();
        String myCode = request.getParameter("newCode");
        String action = request.getParameter("action");
        String url = "";
        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");

        switch (action) {
            case "deleteProduct":

                for (Product newProduct : products) {
                    if (newProduct.getCode().equals(myCode)) {
                        session.setAttribute("deleteCode", myCode);
                        session.setAttribute("currentProduct", newProduct);
                        int index = products.indexOf(newProduct);
                        session.setAttribute("index", index);
                    }
                }
                url = "/confirmDelete.jsp";
                break;
            case "edit":

                for (Product newProduct : products) {
                    if (newProduct.getCode().equals(myCode)) {
                        session.setAttribute("editCode", myCode);
                        session.setAttribute("currentProduct", newProduct);

                        int index = products.indexOf(newProduct);
                        session.setAttribute("index", index);
                    }
                }
                url = "/product.jsp";
                break;

            case "displayProducts":
                url = "/products.jsp";
                break;

            case "addProduct":
                session.removeAttribute("currentProduct");
                session.removeAttribute("editCode");
                url = "/product.jsp";
                break;

            case "delete":
                url = "/products.jsp";
                products.remove((int) session.getAttribute("index"));
                break;

            default:
                try (PrintWriter out = response.getWriter()) {
                    out.println("error: action is invalid");
                }
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
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

        String path = getServletContext().getRealPath("/WEB_INF/products.txt");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String url = "";
        ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");

        switch (action) {
            case "addProduct":
                //Get the the values to put into the new product
                String code = request.getParameter("code");
                String desc = request.getParameter("desc");

                double price = Double.parseDouble(request.getParameter("price"));

                //Create new product object and put in the values
                Product newProduct = new Product();
                newProduct.setCode(code);
                newProduct.setDescription(desc);
                newProduct.setPrice(price);

                if (products == null) {
                    products = new ArrayList<>();

                }

                if (session.getAttribute("editCode") == null) {
                    products.add(newProduct);
                } else {
                    products.set((int) session.getAttribute("index"), newProduct);
                }

                session.setAttribute("products", products);

                //Redirect back to products page
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;

        }

    }
}
