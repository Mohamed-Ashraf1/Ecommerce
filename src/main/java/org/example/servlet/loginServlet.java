package org.example.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.example.entity.Admin;
import org.example.entity.GENDER;
import org.example.entity.User;
import org.example.service.AdminService;
import org.example.service.CartService;
import org.example.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class loginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");

        if (role.equals("User")) {
            EntityManager entityManager = emf.createEntityManager();
            UserService userService = new UserService(entityManager);
            try {
                User user = userService.loginCheck(email, password);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", user);
                    session.setAttribute("role", role);

                    Integer cartSize = new CartService(entityManager).cartProductsCount(user.getId());
                    session.setAttribute("cartSize",cartSize);

                    System.out.println("User added to session: " + session.getAttribute("user"));
                    response.sendRedirect("/ecommerce");

                }

            } catch (RuntimeException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher( "/login.jsp").include(request, response);

            }
        }

        if (role.equals("Admin")) {
            EntityManager entityManager = emf.createEntityManager();
            AdminService adminService = new AdminService(entityManager);
            try {
                Admin admin = adminService.checkAdmin(email,password);
                if (admin != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", admin);
                    session.setAttribute("role", role);

                    System.out.println("Admin added to session: " + session.getAttribute("admin"));
                    response.sendRedirect("/ecommerce/adminDetails");

                }

            } catch (RuntimeException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher( "/login.jsp").include(request, response);

            }
        }
    }
}
