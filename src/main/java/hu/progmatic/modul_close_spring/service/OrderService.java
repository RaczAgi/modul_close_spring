package hu.progmatic.modul_close_spring.service;

import hu.progmatic.modul_close_spring.model.Orders;
import hu.progmatic.modul_close_spring.model.Person;
import hu.progmatic.modul_close_spring.repo.OrderRepo;
import hu.progmatic.modul_close_spring.repo.PersonRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepo orderRepo;
    private PersonRepo personRepo;

    public List<Orders> getAllOrders(){ return orderRepo.findAll();}

    public List<Orders> getAllOrdersByPerson(Person person) {return orderRepo.getAllOrdersByOwner(person);}

    public void saveOrder(Orders order){orderRepo.save(order);}

    @Transactional
    public void deleteOrdersByPerson(Person person) {
        orderRepo.deleteAllByOwner(person);
    }
}
