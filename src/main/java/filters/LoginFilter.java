package filters;

import Model.Session;
import Model.User;
import com.sun.scenario.effect.FilterContext;
import services.SessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter("/home")
public class LoginFilter implements Filter {

    private SessionService sessionService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext filterContext = filterConfig.getServletContext();
        sessionService = (SessionService) filterContext.getAttribute("sessionService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("12345");
        String userId = null;
        String userKey = null;
        for (Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("userId")){
               userId = cookie.getValue();
            }
            if(cookie.getName().equals("userKey")){
                userKey = cookie.getValue();
            }

        }
        if(userId == null || userKey == null){
            response.sendRedirect("/signIn");
        }else{
            boolean signIn =sessionService.check(Session.builder()
                                .user(User.builder()
                                    .id(Long.parseLong(userId))
                                    .build())
                                .key(userKey)
                                .build());
            if(signIn){
                filterChain.doFilter(request, response);
            }else {
                request.getRequestDispatcher("/WEB-INF/jsp/SignInForm.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
