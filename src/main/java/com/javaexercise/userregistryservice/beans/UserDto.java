package com.javaexercise.userregistryservice.beans;

import com.javaexercise.userregistryservice.model.Phone;
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
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
}
