package com.app.nailcare.models;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

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

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Claim> claims;

    @OneToOne(mappedBy = "user")
    private Subscription subscription;
}
