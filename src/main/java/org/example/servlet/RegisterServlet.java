package org.example.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;
import org.example.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
        UserService userService=new UserService(emf.createEntityManager());
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String phone = req.getParameter("phone");
        User u=  userService.registerNewUser(name, email, password, street, city, country, phone, dob, gender);
        if (u!=null){
            HttpSession session= req.getSession(true);
            session.setAttribute("user", u);
            resp.sendRedirect("/ecommerce");

        }else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h1>Registration Failed</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory emf=(EntityManagerFactory) getServletContext().getAttribute("emf");
        UserService userService=new UserService(emf.createEntityManager());
        String email=req.getParameter("email");
        resp.setContentType("text/plain");
        PrintWriter out=resp.getWriter();

        if(userService.checkIfEmailExist(email)) {
            out.println("error");
        }


    }
}
