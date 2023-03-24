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
    @GeneratedValue( strategy = GenerationType.IDENTITY);
    private  Long id;
    //QUEDA PENDIENTE DEFINIR EL TAMANO DE LOS CAMPOS
    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float budget;

    @Column
    private Enum category;

    @Column
    private String imageUrl;





}
