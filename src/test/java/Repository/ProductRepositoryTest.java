package Repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.*;

public class ProductRepositoryTest {

    ProductRepository productRepository;

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/zdstyle";

    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        productRepository = new ProductRepository(dataSource);
    }

    @Test
    public void save() {
    }

    @Test
    public void deleate() {
    }

    @Test
    public void getAll() {
        System.out.println(productRepository.getAll());
    }
}