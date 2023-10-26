package com.app.nailcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Claim> claims;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Subscription subscription;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
}
