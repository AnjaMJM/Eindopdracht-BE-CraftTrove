package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

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
    @NotNull
    @UniqueElements
    private String title;

    @Column(nullable = false)
    @NotNull
    private String description;

    private String thumbnail;

    @ElementCollection
    private List<String> photos;

    @Column(nullable = false)
    @NotNull
    private String pattern;

//    @ManyToOne
//    private Designer designer_id;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "products_keywords",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;

//    @ManyToMany
//    private List<Purchase> purchases;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;




}
