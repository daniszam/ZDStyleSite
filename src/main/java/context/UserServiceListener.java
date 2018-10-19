package context;

import Repository.UserCrudRepository;
import lombok.SneakyThrows;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceListener implements ServletContextListener {


    private UserService usersService;
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/zdstyle";


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);

        UserCrudRepository userCrudRepository = new UserCrudRepository(connection);
        usersService = new UserServiceImpl(userCrudRepository);
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("usersService", usersService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
