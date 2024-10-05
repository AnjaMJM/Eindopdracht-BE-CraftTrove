package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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
    private String name;
    @Column(nullable = false)
    @ManyToMany(mappedBy = "keywords")
    private List<Product> products = new ArrayList<>();
}
