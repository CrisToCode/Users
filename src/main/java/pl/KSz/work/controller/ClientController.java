package pl.KSz.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.KSz.work.service.UserService;
import pl.KSz.work.model.User;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    UserService service;

    @Autowired
    public ClientController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid User user) {
        service.addUser(user);
    }

    @DeleteMapping("/user/{key}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeUser(@PathVariable String key) {
        service.removeUser(key);
    }

    @GetMapping("/user/{key}")
    @ResponseStatus(HttpStatus.CREATED)
    public User getUserByKey(@PathVariable String key) {
        return service.getUserByKey(key);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> getUsersWithSalaryHigherThan(@RequestParam("salary") Integer salary) {
       return service.getUsersWithSalaryHigherThan(salary);
    }
}



