package hu.progmatic.modul_close_spring.controller;

import hu.progmatic.modul_close_spring.service.OrderService;
import hu.progmatic.modul_close_spring.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PageController {
    private PersonService personService;
    private OrderService orderService;

    @GetMapping({"","/","/home"})
    public String getHome(Model model){
        model.addAttribute("persons", personService.getAllPerson());
        return "home";
    }
}
