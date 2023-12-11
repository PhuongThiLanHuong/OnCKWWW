package com.example.onck2.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id",nullable = false)
    private long id;
    @Column(name="category_name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    public Category(){}

    public Category(long id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
