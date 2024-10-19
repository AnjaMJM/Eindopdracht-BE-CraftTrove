package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchases")
@Getter
@Setter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToMany(mappedBy = "purchases", fetch = FetchType.LAZY)
    @Column(nullable = false)
    private List<Product> products;


}
