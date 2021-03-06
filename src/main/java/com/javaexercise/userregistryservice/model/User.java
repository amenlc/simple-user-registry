package com.javaexercise.userregistryservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no debe estar vacio")
    private String name;

    @Pattern(regexp=".+@.+\\.[a-z]+", message = "El email debe estar bien formado")
    private String email;

    //[A-Z]{1}[a-z]*\d{2}
    @Pattern(regexp="[A-Z]{1}[a-z]*\\d{2}", message = "La clave debe estar bien formada")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;

    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
}
