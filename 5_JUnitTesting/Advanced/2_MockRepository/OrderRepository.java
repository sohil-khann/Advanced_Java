import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(int id);

    Order save(Order order);

    boolean deleteById(int id);
}
