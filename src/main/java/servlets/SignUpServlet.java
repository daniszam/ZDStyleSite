package servlets;

import Model.Session;
import Model.User;
import Repository.UserCrudRepository;
import forms.SignInForm;
import forms.SignUpForm;
import lombok.SneakyThrows;
import services.SessionService;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.sql.Date;


@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService usersService;
    private SessionService sessionService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        usersService = (UserService) servletContext.getAttribute("usersService");
        sessionService = (SessionService) servletContext.getAttribute("sessionService");
    }

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("/WEB-INF/jsp/SignUpForm.jsp").forward(request, response);
    }


    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("repassword");
        String country = request.getParameter("country");
        String sex = request.getParameter("sex");
        String date = request.getParameter("birthday");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date(simpleDateFormat.parse(date).getTime());

        SignUpForm signUpForm = SignUpForm.builder()
                .email(email)
                .password(password)
                .rePassword(rePassword)
                .country(country)
                .sex(sex.charAt(0))
                .birthday(birthday)
                .build();


        PrintWriter printWriter = response.getWriter();
        if (usersService.signUp(signUpForm)) {
           // printWriter.print("Correct");
            User user = usersService.getUser(SignInForm.builder()
                    .email(email)
                    .password(password)
                    .build())
                    .get();
            sessionService.create(user);
            response.sendRedirect("/signIn");
        } else {
            printWriter.println("<script type=\"text/javascript\">");
            printWriter.println("alert('User or password incorrect');");
            printWriter.println("location='/signUp';");
            printWriter.println("</script>");
        }

    }
}
