package com.crafter.crafttroveapi.models;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    private boolean active;

    public Role() {

    }

    public Role(RoleEnum name) {
        this.name = name;
    }

}
