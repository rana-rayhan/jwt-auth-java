package jwt.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class HomeController {

    @GetMapping
    public String getUsers() {
        return "new String() users";
    }

    @GetMapping("/data")
    public String getData() {
        return "new String()";
    }

}
