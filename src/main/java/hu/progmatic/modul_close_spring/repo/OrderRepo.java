package hu.progmatic.modul_close_spring.repo;

import hu.progmatic.modul_close_spring.model.Order;
import hu.progmatic.modul_close_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Integer deleteAllByOwner(Person person);

    List<Order> getOrdersByOwner(Person person);

    List<Order> getAllOrdersByOwner(Person person);

}
