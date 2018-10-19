package Repository;

public interface CrudRepository<T> {
    void save (T model);
    void deleate (T model);
}
