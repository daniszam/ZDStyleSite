package Repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save (T model);
    void deleate (T model);
    List<T> getAll();
    Optional<T> getOne(Long id);

}
