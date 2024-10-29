package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "designers")
@Getter
@Setter
public class Designer extends User {

    @Column(nullable = false, unique = true)
    private String brandName;

    private String brandLogo;

    private String brandDescription;

    @OneToMany(mappedBy = "designer", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Product> products;

}
