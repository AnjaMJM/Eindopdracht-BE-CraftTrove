package com.crafter.crafttroveapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] fileData;

    private String url;

    private String contentType;

    private long size;

    @OneToOne(mappedBy = "brandLogo")
    private Designer designer;

}
