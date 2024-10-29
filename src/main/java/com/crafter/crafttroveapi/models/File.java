package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] fileData;

    private String contentType;

    private long size;

    @OneToOne(mappedBy = "logo")
    private Designer designer; //mapped

    @OneToOne(mappedBy = "thumbnail")
    private Product thumbnail;

    @OneToOne(mappedBy = "pattern")
    private Product pattern;

//    @ManyToOne
//    private Product photos;
}
