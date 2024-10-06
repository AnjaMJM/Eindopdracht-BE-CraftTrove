package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keywords")
@Getter
@Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @UniqueElements
    private String name;

    @Column(nullable = false)
    @NotNull
    @ManyToMany(mappedBy = "keywords")
    private List<Product> products = new ArrayList<>();
}
