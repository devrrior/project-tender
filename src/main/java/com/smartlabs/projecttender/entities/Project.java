package com.smartlabs.projecttender.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column()
    private String responsibleConstructor;

    @Column
    private Float budget;

    @Column
    private String type;

    @Column
    private String imageUrl;


}
