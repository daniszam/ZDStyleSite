package Repository;

import Model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements CrudRepository<Product> {



    //language=SQL
    public static final String SQL_GET_ALL_PRODUCT = "SELECT * FROM product";

    private JdbcTemplate jdbcTemplate;


    public ProductRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    private RowMapper<Product> productRowMapper = ((resultSet, i) -> Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .img(resultSet.getString("img"))
            .build());


    @Override
    public void save(Product model) {

    }

    @Override
    public void deleate(Product model) {

    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_PRODUCT, productRowMapper);
    }

    @Override
    public Optional<Product> getOne(Long id) {
        return null;
    }
}
