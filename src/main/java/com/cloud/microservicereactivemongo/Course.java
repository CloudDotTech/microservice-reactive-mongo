package com.cloud.microservicereactivemongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
@Data
@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Course {
    private String id;
    private String name;

    public Course(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    private Long price;
    public Course(String id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
