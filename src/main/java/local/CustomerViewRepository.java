package local;
import org.springframework.data.repository.CrudRepository;
public interface CustomerViewRepository extends CrudRepository<OrderStatus, Long> {
}
