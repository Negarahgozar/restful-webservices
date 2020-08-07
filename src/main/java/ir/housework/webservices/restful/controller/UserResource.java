package ir.housework.webservices.restful.controller;

import ir.housework.webservices.restful.models.User;
import ir.housework.webservices.restful.service.UserDaoService;
import ir.housework.webservices.restful.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

    private UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    //retrieve all users
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }



    //retrieve user(int id)
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        Optional<User> user = userDaoService.deleteById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        EntityModel<User> resource = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-user"));
        return resource;
    }

    //retrieve user(int id)
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        Optional<User> user = userDaoService.deleteById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);
    }

    // POST
    // input: the details of user
    // output: create and return the created URI of the created user
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        System.out.println(uri.getPath());
        return ResponseEntity.created(uri).build();
    }

}
