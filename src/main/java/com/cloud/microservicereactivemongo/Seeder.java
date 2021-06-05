package com.cloud.microservicereactivemongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    UserRepository repository;

    public Seeder(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        List<User> list = Arrays.asList(
                new User("firstName0", "lastName0", "email0", "password0", Arrays.asList(
                        new Course("CourseName0", 123L),
                        new Course("CourseName1", 234L),
                        new Course("CourseName2", 345L))),
                new User("firstName1", "lastName1", "email1", "password1", Arrays.asList(
                        new Course("CourseName1", 123L),
                        new Course("CourseName3", 234L))),
                new User("firstName2", "lastName2", "email2", "password2", Arrays.asList(
                        new Course("CourseName1", 123L))),
                new User("firstName3", "lastName3", "email3", "password3", Arrays.asList()));
        this.repository.deleteAll();
        this.repository.saveAll(list);
    }
}
