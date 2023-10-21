package hu.progmatic.modul_close_spring.controller;

import hu.progmatic.modul_close_spring.model.Orders;
import hu.progmatic.modul_close_spring.model.Person;
import hu.progmatic.modul_close_spring.service.OrderService;
import hu.progmatic.modul_close_spring.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;
    private PersonService personService;


    @GetMapping("/{id}")
    public String getOrderPersonById(Model model, @PathVariable Long id) {
        Person person = personService.getPersonById(id);
        model.addAttribute("expenses", orderService.getAllOrdersByPerson(person));
        return "orders";
    }


    @GetMapping("/new/{person-id}")
    public String createOrder(Model model, @PathVariable("person-id") Long personId){
        model.addAttribute("personId", personId);
        model.addAttribute("order", new Orders());
        return "order_form";
    }

    @PostMapping("/add/{person-id}")
    public String createOrder(@ModelAttribute("order") Orders order, @PathVariable("person-id") Long personId){
        order.setOwner(personService.getPersonById(personId));
        orderService.saveOrder(order);
        return "redirect:/person";
    }
}
