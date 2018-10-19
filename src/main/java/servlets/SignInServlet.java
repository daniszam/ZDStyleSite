package servlets;

import Repository.UserCrudRepository;
import forms.SignInForm;
import lombok.SneakyThrows;
import services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UserService)servletContext.getAttribute("usersService");

    }

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        request.getRequestDispatcher("/WEB-INF/jsp/SignInForm.jsp").forward(request, response);
    }

    @SneakyThrows
    public void doPost (HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        SignInForm signInForm = new SignInForm(email, password);
        usersService.signIn(signInForm) ;
        response.sendRedirect("/home");

    }
}
