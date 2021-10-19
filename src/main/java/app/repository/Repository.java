package app.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T,U> {
    List<T> getAll();
    Optional<T> find(U id);
    void add(T item);
    void delete(T item);
}
