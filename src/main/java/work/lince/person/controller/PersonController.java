package work.lince.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import work.lince.person.model.Person;
import work.lince.person.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(path = "/people")
public class PersonController {

    @Autowired
    protected PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@RequestBody @Validated Person body) {
        return service.create(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable("id") final Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id") final Long id) {
        service.remove(id);
    }

}