package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "designers")
@Getter
@Setter
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @UniqueElements
    private String designerName;

    @Column(nullable = false, unique = true)
    @NotNull
    @UniqueElements
    private String brandName;

    private String shopDescription;

    @OneToOne(mappedBy = "isDesigner")
    private User user;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> products;


}
