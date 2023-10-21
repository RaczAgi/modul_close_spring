package hu.progmatic.modul_close_spring.service;

import hu.progmatic.modul_close_spring.model.Orders;
import hu.progmatic.modul_close_spring.model.Person;
import hu.progmatic.modul_close_spring.repo.OrderRepo;
import hu.progmatic.modul_close_spring.repo.PersonRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepo personRepo;
    private OrderRepo orderRepo;

    public List<Person> getAllPerson() {
        return personRepo.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepo.findById(id).orElse(null);
    }

    public List<Orders> getAllOrdersByPerson(Person person) {
        return orderRepo.getOrdersByOwner(person);
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }
    @Transactional
    public void deletePersonById(Long id) {
        personRepo.deleteById(id);
    }

    public Person getHighestOrderValuePerson() {
        Map<Person, Integer> personOrders = fillPersonsMap();
        return getMaxHighestOrderValuePerson(personOrders);
    }

    private Map<Person, Integer> fillPersonsMap() {
        Map<Person,Integer> personOrders = new HashMap<>();
        fillPersonsMapWithPerson(personOrders);
        fillPersonsMapWithOrders(personOrders);
        return personOrders;
    }

    private void fillPersonsMapWithOrders(Map<Person, Integer> personOrders) {
        List<Orders> orders = getAllOrders();
        for (Orders actual : orders){
            Person actualPerson = actual.getOwner();
            personOrders.put(actualPerson, personOrders.get(actualPerson) + actual.getAmount());
        }
    }

    private List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }


    private void fillPersonsMapWithPerson(Map<Person, Integer> personOrders) {
        List<Person> persons = getAllPerson();
        for(Person actual : persons){
            personOrders.put(actual, 0);
        }
    }

    private Person getMaxHighestOrderValuePerson(Map<Person, Integer> personOrders) {
        int max = 0;
        for (var actual: personOrders.entrySet()) {
            if (actual.getValue() > max) {
                max = actual.getValue();
            }
        }for (var actual: personOrders.entrySet()){
            if(actual.getValue() == max){
                return actual.getKey();
            }
        } return null;
    }
}
