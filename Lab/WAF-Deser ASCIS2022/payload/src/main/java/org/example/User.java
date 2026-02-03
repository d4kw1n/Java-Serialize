package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L; // Nên có để đảm bảo tương thích
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }
}
