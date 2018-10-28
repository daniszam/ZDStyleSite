package context;

import Model.Product;
import Repository.CartRepository;
import Repository.ProductRepository;
import Repository.SessionRepository;
import Repository.UserCrudRepository;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import services.SessionService;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceListener implements ServletContextListener {


    private UserService usersService;
    private SessionService sessionService;
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/zdstyle";


    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Class.forName("org.postgresql.Driver");
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        UserCrudRepository userCrudRepository = new UserCrudRepository(connection);
        productRepository = new ProductRepository(dataSource);
        usersService = new UserServiceImpl(userCrudRepository);
        sessionService = new SessionService(new SessionRepository(dataSource));
        cartRepository = new CartRepository(dataSource);

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("cartRepository", cartRepository);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("sessionService", sessionService);
        servletContext.setAttribute("productRepository", productRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
