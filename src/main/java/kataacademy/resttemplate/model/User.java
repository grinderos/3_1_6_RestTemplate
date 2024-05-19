package kataacademy.resttemplate.model;

import lombok.Data;

@Data
public class User {
    public int id;
    public String name;
    public String lastName;
    public int age;

    public User() {
    }

    public User(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName =lastName;
        this.age = age;
    }
}
