package org.example.servlet;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.User;
import org.example.service.UserService;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class UserDetailsServlet extends HttpServlet {

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String id = request.getParameter("userId");

        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");

        UserService userService = new UserService(emf.createEntityManager());

        try {
            User user = userService.findUserById(Integer.parseInt(id));

            // Format the date to yyyy-MM-dd (HTML5 date format)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfBirth = sdf.format(user.getDateOfBirth());

            request.setAttribute("user", user);
            request.setAttribute("dateOfBirth", dateOfBirth);


            request.getRequestDispatcher("/userDetails.jsp").forward(request, response);
        }catch (RuntimeException e)
        {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/userDetails.jsp").forward(request, response);
        }
    }
}
