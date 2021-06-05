package com.cloud.microservicereactivemongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public Flux<UserDto> getUsers() {
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<UserDto> getUser(String id) {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

//    public Flux<UserDto> getUserInRange(double min, double max) {
//        return repository.findByPriceBetween(Range.closed(min, max));
//    }

    public Mono<UserDto> saveUser(Mono<UserDto> userDtoMono) {
        return userDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<UserDto> updateUser(Mono<UserDto> userDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p -> userDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);

    }

    public Mono<Void> deleteUser(String id) {
        return repository.deleteById(id);
    }
}