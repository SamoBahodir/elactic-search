package com.example.api;

import com.example.model.person.Person;
import com.example.model.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService service;

    @PostMapping()
    public void save(@RequestBody Person person) {
        service.save(person);
    }

    @GetMapping("/{id}")
    public Person getId(@PathVariable String id) {
        return service.findById(id);
    }
}
