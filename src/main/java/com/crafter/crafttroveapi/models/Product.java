package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private String description;
    private String thumbnail;
    @ElementCollection
    private List<String> photos = new ArrayList<>();
    @Column(nullable = false)
    private String pattern;
//    @ManyToOne
//    private Designer designer_id;
    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "product_keywords",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;
//    @ManyToMany
//    private List<Purchase> purchases;
//    @ManyToOne
//    private List<Review> reviews;




}
