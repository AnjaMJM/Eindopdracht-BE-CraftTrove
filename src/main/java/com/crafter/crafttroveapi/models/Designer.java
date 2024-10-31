package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "designers")
@Getter
@Setter
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "designer")
    private User user;

    @Column(nullable = false, unique = true)
    private String brandName;

    @OneToOne
    @JoinColumn(name = "logo_file", referencedColumnName = "name")
    private File brandLogo;

    private String brandDescription;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> products;

}
