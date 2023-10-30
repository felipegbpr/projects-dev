package br.com.spb.devolution.controllers;

import br.com.spb.devolution.models.User;
import br.com.spb.devolution.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
        System.out.println("O id é " + id);

        Optional<User> userFind = this.userRepository.findById(id);

        if(userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/")
    public User user(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        return this.userRepository.findByIdGreaterThan(id);
    }

    @GetMapping("findByName/{name}")
    public List<User> findByName(@PathVariable("name") String name) {
        return this.userRepository.findByNameIgnoreCase(name);
    }
}
