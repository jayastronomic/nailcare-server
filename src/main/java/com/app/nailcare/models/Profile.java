package com.app.nailcare.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profiles")
public class Profile extends ApplicationEntity<Profile> {
    @Column(nullable = false)
    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name can not be blank")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "City can not be blank")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "State can not be blank")
    private String state;

    @Column(nullable = false)
    @NotBlank(message = "Street address can not be blank")
    private String streetAddress;

    @Column(nullable = false)
    @NotBlank(message = "Zip code can not be blank")
    private String zipCode;

    @Column
    private String unitNumber;

    @Column(nullable = false)
    @NotNull(message = "Birth date can not be blank")
    private LocalDate birthdate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

