package com.example.onck2.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQueries({
        @NamedQuery(
                name = "Product.findAllByCategory",
                query = "select p from Product p where p.category.id =: id"
        )
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id",nullable = false)
    private long id;
    @Column(name="Product_name",nullable = false)
    private String name;
    @Column(name="product_price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    public Product(){}

    public Product(long id) {
        this.id = id;
    }
    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(long id, String name, double price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
