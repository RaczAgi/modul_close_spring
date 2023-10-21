package hu.progmatic.modul_close_spring.repo;

import hu.progmatic.modul_close_spring.model.Orders;
import hu.progmatic.modul_close_spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

    Integer deleteAllByOwner(Person person);

    List<Orders> getOrdersByOwner(Person person);

    List<Orders> getAllOrdersByOwner(Person person);

}
