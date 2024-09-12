package org.example.servlet;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.service.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class showProductsServlet extends HttpServlet {



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve subcategories (list of category names or IDs)
        String[] subCategoriesArray = request.getParameterValues("subCategories");
        List<String> subCategories = (subCategoriesArray != null && subCategoriesArray.length > 0)
                ? Arrays.asList(subCategoriesArray)
                : null;

        System.out.println(subCategories.size());
        // Retrieve min and max price
        Double minPrice = null;
        Double maxPrice = null;

        String minPriceParam = request.getParameter("minPrice");
        if (minPriceParam != null && !minPriceParam.isEmpty()) {
            minPrice = Double.parseDouble(minPriceParam);
        }

        String maxPriceParam = request.getParameter("maxPrice");
        if (maxPriceParam != null && !maxPriceParam.isEmpty()) {
            maxPrice = Double.parseDouble(maxPriceParam);
        }


        // Retrieve sortByPrice and sortByQuantity flags
        boolean sortByPrice = false;
        boolean sortByQuantity = false;
        String sortByPriceParam = request.getParameter("sortByPrice");
        String sortByQuantityParam = request.getParameter("sortByQuantity");

        if (sortByPriceParam != null) {
            sortByPrice = Boolean.parseBoolean(sortByPriceParam);  // Defaults to false if parsing fails
        }
        if (sortByQuantityParam != null) {
            sortByQuantity = Boolean.parseBoolean(sortByQuantityParam);  // Defaults to false if parsing fails
        }


        // Retrieve pagination values with default values and safe parsing
        int pageNumber = 1;  // Default to page 1
        int pageSize = 9;    // Default to 9 products per page


        String pageNumberParam = request.getParameter("pageNumber");
        if (pageNumberParam != null && !pageNumberParam.isEmpty()) {
            pageNumber = Integer.parseInt(pageNumberParam);
        }


        EntityManagerFactory emf = (EntityManagerFactory) request.getServletContext().getAttribute("emf");

        ProductService productService = new ProductService(emf.createEntityManager());

        try {
            List<Product> products = productService.findAllProducts(subCategories, minPrice,  maxPrice,  sortByPrice,  sortByQuantity, pageNumber,  pageSize);

            ///////////////////////////////// continue from here ///////////////////////////////

            long totalProducts = products.size();
            int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

            request.setAttribute("products", products);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", pageNumber);

            request.getRequestDispatcher("/test.jsp").forward(request,response);

        }catch (RuntimeException e)
        {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
