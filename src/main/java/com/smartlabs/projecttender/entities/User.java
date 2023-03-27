package com.smartlabs.projecttender.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY);
    private Long id;
    @Column
    private String email;
    @Column
    private String companyName;
    @Column
    private String password;
}
