package com.learning.entity;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "The firstName is required.")
    private String firstName;

    @Column
    private String lastName;

    @Column
    @NotBlank(message = "The email is required.")
    private String email;

    @Column
    private Date dateOfBirth;

    @Column
    private String gender;

    @NotBlank(message = "The address is required.")
    @Column
    private String address;

    @Size(min = 10, max = 10, message = "phone number must be exactly 10 characters long.")
    @NotBlank(message = "The phoneNumber is required.")
    @Column(unique = true)
    private String phoneNumber;
}
