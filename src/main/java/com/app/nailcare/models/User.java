package com.app.nailcare.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User extends ApplicationEntity<User> {
    @Email
    @NotBlank(message = "Email can not be blank")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password can not be blank")
    @Column(nullable = false)
    private String password;
}
