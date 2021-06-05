package com.cloud.microservicereactivemongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public Flux<UserDto> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getUser(@PathVariable String id) {
        return service.getUser(id);
    }

//    @GetMapping("/range")
//    public Flux<UserDto> getUserBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
//        return service.getUserInRange(min,max);
//    }

    @PostMapping
    public Mono<UserDto> saveUser(@RequestBody Mono<UserDto> userDtoMono) {
        System.out.println("controller method called ...");
        return service.saveUser(userDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<UserDto> updateUser(@RequestBody Mono<UserDto> userDtoMono, @PathVariable String id) {
        return service.updateUser(userDtoMono, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return service.deleteUser(id);
    }
}
