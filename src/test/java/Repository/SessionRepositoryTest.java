package Repository;

import Model.Session;
import Model.User;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLOutput;
import java.util.Optional;


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
    @Ignore
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
    @Ignore
    public void deleate(){
        sessionRepository.deleate(2L);
    }

    @Test
    @Ignore
    public void get() {
        Optional<Session> sessionOptional = sessionRepository.get(2L);
        Session session = sessionOptional.get();
        System.out.println(session);
    }

    @Test
    @Ignore
    public void updateKey() {
        Session session = Session.builder()
                .user((sessionRepository.get(2L)).get().getUser())
                .key("dhkjjkhdjksdvkjhvsdjkhsvdk")
                .id(2L)
                .build();
        sessionRepository.updateKey(session, "newKey");
    }

    @Test
    public void getKey(){
        System.out.println(sessionRepository.getKey(2L));
    }
}