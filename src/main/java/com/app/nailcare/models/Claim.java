package com.app.nailcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "claims")
public class Claim extends ApplicationEntity<Claim>{
    @NotNull(message = "Claim Date can not be blank")
    @Column(nullable = false, updatable = false, unique = true)
    private LocalDate claimDate;

    @NotBlank(message = "Description can not be blank")
    @Column(nullable = false, updatable = false)
    private String description;

    @NotBlank(message = "Affected Nails can not be blank")
    @Column(nullable = false, updatable = false)
    private String affectedNails;

    @Column(nullable = false)
    private String claimStatus;

    @NotNull(message = "Claim Amount can not be blank")
    @Column(nullable = false, updatable = false)
    private Double claimAmount;

    @Column(updatable = false)
    private LocalDate resolutionDate;

    @Column(updatable = false)
    private LocalDate reviewDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;
}
