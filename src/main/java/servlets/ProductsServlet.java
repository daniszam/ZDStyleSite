package servlets;

import Model.Product;
import Repository.CartRepository;
import Repository.ProductRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ProductRepository productRepository;
    private CartRepository cartRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        productRepository = (ProductRepository) servletContext.getAttribute("productRepository");
        cartRepository = (CartRepository) servletContext.getAttribute("cartRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", productRepository.getAll());

        long userId=0;
        for (Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("userId")){
                userId = Long.parseLong(cookie.getValue());
            }
        }
        List<Product> productList = cartRepository.getOne(userId).get().getProducts();
        if(productList==null){
            productList = new ArrayList<>();
        }else {
            if (productList.get(0).getId() == 0) {
                productList.clear();
            }
        }
        System.out.println(productList);
        request.setAttribute("cart", productList);
        request.getRequestDispatcher("/WEB-INF/jsp/ProductsPage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("name"));
        Product product = Product.builder()
                .name(request.getParameter("name"))
                .id(Long.parseLong(request.getParameter("id")))
                .build();
        long userId = 0;
        for (Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("userId")){
                userId = Long.parseLong(cookie.getValue());
            }
        }
        cartRepository.addProductToCart(product.getId(), cartRepository.getOne(userId).get().getId());
        request.setAttribute("cart", cartRepository.getOne(userId).get().getProducts());
        System.out.println(userId);
        System.out.println(cartRepository.getOne(userId));
        System.out.println(cartRepository.getOne(userId));
        //request.setAttribute("cart", cartRepository.getOne(userId));
        System.out.println(product);

    }
}
