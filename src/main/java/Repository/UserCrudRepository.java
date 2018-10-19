package Repository;

import Model.User;
import lombok.SneakyThrows;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class UserCrudRepository implements CrudRepository<User> {

    private Connection connection;

    //language=SQL
    public static final String SQL_SAVE_USER =
            "INSERT INTO zdstyle_user (email, hash_password, sex, birthday, country) VALUES (?,?,?,?,?)";


    //language=SQL
    public static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM zdstyle_user WHERE email=?";

    private mappers.RowMapper<User> userRowMapper= new mappers.RowMapper<User>() {
        @Override
        @SneakyThrows
        public User rowMap(ResultSet resultSet) {
           return User.builder()
                   .email(resultSet.getString("email"))
                   .country(resultSet.getString("country"))
                   .hashPassword(resultSet.getString("hash_password"))
                   .birthday(resultSet.getDate("birthday"))
                   .sex(resultSet.getString("sex").charAt(0))
                   .build();
        }
    };


    public UserCrudRepository(Connection connection){
        this.connection = connection;
        System.out.println(connection);
    }

    @Override
    @SneakyThrows
    public void save(User model) {
        System.out.println(model);
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER);
        preparedStatement.setString(1, model.getEmail());
        preparedStatement.setString(2, model.getHashPassword());
        preparedStatement.setString(3, String.valueOf(model.getSex()));
        preparedStatement.setDate(4, model.getBirthday());
        preparedStatement.setString(5,model.getCountry());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    public Optional<User> fingOneByEmail(String email){
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
        preparedStatement.setString(1,email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            return null;
        }
        return Optional.of(userRowMapper.rowMap(resultSet));
    }

    @Override
    public void deleate(User model) {

    }
}
