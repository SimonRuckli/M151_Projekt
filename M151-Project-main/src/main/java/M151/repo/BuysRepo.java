package M151.repo;

import M151.model.Article;
import M151.model.Buys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuysRepo  extends CrudRepository<Buys, Long> {
    Buys findById(long id);
}