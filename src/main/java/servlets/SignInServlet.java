package servlets;

import Model.User;
import Repository.UserCrudRepository;
import forms.SignInForm;
import lombok.SneakyThrows;
import services.SessionService;
import services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService usersService;
    private SessionService sessionService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UserService)servletContext.getAttribute("usersService");
        sessionService = (SessionService) servletContext.getAttribute("sessionService");

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
        System.out.println("1231241414");
        usersService.signIn(signInForm);
        System.out.println("12124");
        User user = usersService.getUser(signInForm).get();

        System.out.println(user);
        Cookie userId = new Cookie("userId", user.getId().toString());
        userId.setMaxAge(60*5);
        response.addCookie(userId);


        String key = sessionService.getKey(user);
        System.out.println(key);
        Cookie userKey = new Cookie("userKey", key);
        userKey.setMaxAge(60*5);
        response.addCookie(userKey);

        response.sendRedirect("/home");

    }
}
