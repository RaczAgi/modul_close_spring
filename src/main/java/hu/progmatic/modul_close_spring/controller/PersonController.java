package hu.progmatic.modul_close_spring.controller;

import hu.progmatic.modul_close_spring.model.Person;
import hu.progmatic.modul_close_spring.service.OrderService;
import hu.progmatic.modul_close_spring.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;
    private OrderService orderService;


    @GetMapping("/{id}")
    public String getPersonById(Model model, @PathVariable Long id) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);

        return "person";
    }

    @GetMapping("/new")
    public String createPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person_form";
    }
    @PostMapping("/add")
    public String createPerson(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String updatePerson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", personService.getPersonById(id));
        return "person_update";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, @ModelAttribute("person") Person updated) {
        personService.savePerson(updated);
        return "redirect:/home";
    }


    @GetMapping("/high-value-order")
    public String getHighestOrderValue(Model model) {
        Person person = personService.getHighestOrderValuePerson();
        model.addAttribute("person", person);
        model.addAttribute("orders", orderService.getAllOrdersByPerson(person));
        return "person";
    }

}
