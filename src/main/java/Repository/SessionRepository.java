package Repository;

import Model.Session;
import Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

public class SessionRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    public static final String SQL_INSERT_SESSION = "INSERT INTO session (user_id, key) VALUES (?,?)";

    //language=SQL
    public static final String SQL_DELEATE_SESSION = "DELETE FROM session WHERE id=?";

    //language=SQL
    public static final String SQL_SEARCH_SESSION_BY_KEY = "SELECT *, session.id AS session_id FROM session LEFT JOIN zdstyle_user u on session.user_id = u.id" +
            " WHERE session.user_id=?";

    //language=SQL
    public static final String SQL_UPDATE_KEY = "UPDATE session " +
            "SET key=? " +
            "WHERE session.user_id=? AND session.key=?";

    //language=SQL
    public static final String SQL_GET_KEY_BY_USER_ID = "SELECT *, session.id AS session_id FROM session LEFT JOIN zdstyle_user u on session.user_id = u.id WHERE user_id=?";

    public SessionRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Session> sessionRowMapper = (resultSet, i) -> Session.builder()
            .key(resultSet.getString("key"))
            .id(resultSet.getLong("session_id"))
            .user(User.builder()
                .email(resultSet.getString("email"))
                .hashPassword(resultSet.getString("hash_password"))
                .id(resultSet.getLong("user_id"))
                .build())
            .build();


    public void create (Session session){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_SESSION, new String[] {"id"});
                    ps.setLong(1, session.getUser().getId());
                    ps.setString(2, session.getKey());
                    return ps;
                }, keyHolder);

        session.setId(keyHolder.getKey().longValue());
    }

    public void deleate(Long id){
        jdbcTemplate.update(SQL_DELEATE_SESSION, id);
    }

    public Optional<Session> get(Long userId){
        return Optional.of(jdbcTemplate.queryForObject(SQL_SEARCH_SESSION_BY_KEY, sessionRowMapper, userId));
    }

    public void updateKey(Session session, String newKey){
        jdbcTemplate.update(SQL_UPDATE_KEY, newKey, session.getUser().getId(), session.getKey());
    }

    public Optional<Session> getKey(Long userId){
        return Optional.of(jdbcTemplate.queryForObject(SQL_GET_KEY_BY_USER_ID, sessionRowMapper, userId));
    }




}
