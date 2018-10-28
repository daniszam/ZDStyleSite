package Repository;

import Model.Cart;
import Model.Product;
import Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CartRepository implements CrudRepository<Cart> {

    //language=SQL
    public static final String SQL_SAVE_CART = "INSERT INTO cart (user_id) VALUES (?)";

    //language=SQL
    public static final String SQL_DELEATE_CART = "DELETE FROM cart WHERE id=?";

    //language=SQL
    public static final String SQL_GET_CART_BY_ID = "SELECT *, cart.id AS cart_id FROM cart LEFT JOIN zdstyle_user u on cart.user_id = u.id" +
            " WHERE cart.id=?";

    //language=SQL
    public static final String SQL_INSERT_PRODUCT_TO_CART = "INSERT INTO cart_product (cart_id, product_id) VALUES (?,?)";


    //language=SQL
    public static final String SQL_GET_CART_WITH_PRODUCT = "SELECT * FROM cart LEFT JOIN zdstyle_user u on cart.user_id = u.id" +
            " LEFT JOIN cart_product  on cart.id = cart_product.cart_id LEFT JOIN product p on cart_product.product_id = p.id WHERE cart.user_id=?";
    private JdbcTemplate jdbcTemplate;
    private Map<Cart, List<Product>> cartProductsMap;
    private Cart theOnlyCart;

    public CartRepository (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private RowMapper<Cart> cartRowMapper = ((resultSet, i) -> Cart.builder()
            .id(resultSet.getLong("cart_id"))
            .user(User.builder()
                    .id(resultSet.getLong("user_id"))
                    .hashPassword(resultSet.getString("hash_password"))
                    .email(resultSet.getString("email"))
                    .birthday(resultSet.getDate("birthday"))
                    .country(resultSet.getString("country"))
                    .sex(resultSet.getString("sex").charAt(0))
                    .build())
            .build());


    private RowMapper<Cart> cartWithProductsRowMapper = new RowMapper<Cart>() {
        @Override
        public Cart mapRow(ResultSet resultSet, int i) throws SQLException {
           if(cartProductsMap.size() == 0){
               Cart cart = cartRowMapper.mapRow(resultSet, i);
               cartProductsMap.put(cart, new ArrayList<>());
               theOnlyCart = cart;
           }
           Product product = Product.builder()
                   .id(resultSet.getLong("product_id"))
                   .img(resultSet.getString("img"))
                   .name(resultSet.getString("name"))
                   .build();
           cartProductsMap.get(theOnlyCart).add(product);
           return theOnlyCart;
        }
    };



    @Override
    public void save(Cart model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_SAVE_CART, new String[] {"id"});
                    ps.setLong(1, model.getUser().getId());
                    return ps;
                }, keyHolder);

        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void deleate(Cart model) {
        jdbcTemplate.update(SQL_DELEATE_CART, model.getId());
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }

    @Override
    public Optional<Cart> getOne(Long id) {
        cartProductsMap = new HashMap<>();
        jdbcTemplate.query(SQL_GET_CART_WITH_PRODUCT, cartWithProductsRowMapper, id);
        theOnlyCart.setProducts(cartProductsMap.get(theOnlyCart));
        Cart result = theOnlyCart;
        theOnlyCart = null;
        return Optional.of(result);
    }

    public void addProductToCart(Long productId, Long cardId){
        jdbcTemplate.update(SQL_INSERT_PRODUCT_TO_CART, cardId, productId);

    }
}
