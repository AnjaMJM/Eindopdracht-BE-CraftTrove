package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean canManageUsers;

    @Column(columnDefinition = "boolean default false")
    private Boolean canManageProducts;

    @Column(columnDefinition = "boolean default false")
    private Boolean canDeleteReviews;

    @Column(columnDefinition = "boolean default false")
    private Boolean canManageKeywords;

    @Column(columnDefinition = "boolean default false")
    private Boolean canManageShopSettings;

    @Column(columnDefinition = "boolean default false")
    private Boolean vanManagePurchases;
}
