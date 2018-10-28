package services;

import Model.User;
import Repository.SessionRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.Assert.*;

public class SessionServiceTest {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dREAM1cACAO";
    private static final String URL = "jdbc:postgresql://localhost:5432/zdstyle";

    SessionService sessionService;


    @Before
    public void setUp() throws Exception {
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        sessionService = new SessionService(new SessionRepository(dataSource));

    }

    @Test
    @Ignore
    public void create() {
        sessionService.create(User.builder()
                .id(3L)
                .hashPassword("sfdgsd")
                .build());
    }

    @Test
    public void check() {
    }

    @Test
    public void getKey() {
    }
}