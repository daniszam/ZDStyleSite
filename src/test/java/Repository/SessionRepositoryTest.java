package Repository;

import Model.Session;
import Model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;

import static org.junit.Assert.*;
import static sun.plugin.javascript.navig.JSType.URL;

public class SessionRepositoryTest {

    SessionRepository sessionRepository;

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

        sessionRepository = new SessionRepository(dataSource);
    }

    @Test
    public void create() {
        User user = User.builder()
                .id(2L)
                .build();
        Session session = Session.builder()
                .key("dhkjjkhdjksdvkjhvsdjkhsvdk")
                .user(user)
                .build();
        sessionRepository.create(session);
    }

    @Test
    public void deleate(){
        sessionRepository.deleate(2L);
    }

    @Test
    public void get() {
        Optional<Session> sessionOptional = sessionRepository.get(2L, "dhkjjkhdjksdvkjhvsdjkhsvdk");
        Session session = sessionOptional.get();
        System.out.println(session);
    }

    @Test
    public void updateKey() {
        Session session = Session.builder()
                .user((sessionRepository.get(2L,"dhkjjkhdjksdvkjhvsdjkhsvdk")).get().getUser())
                .key("dhkjjkhdjksdvkjhvsdjkhsvdk")
                .id(2L)
                .build();
        sessionRepository.updateKey(session, "newKey");
    }
}