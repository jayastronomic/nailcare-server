package com.app.nailcare.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coverages")
public class Coverage extends ApplicationEntity<Coverage> {
    @NotBlank(message = "Plan name can not be blank")
    @Column(nullable = false)
    private String planName;

    @NotBlank(message = "Plan description can not be blank")
    @Column(nullable = false)
    private String planDescription;

    @NotBlank(message = "Coverage limit can not be blank")
    @Column(nullable = false)
    private Integer coverageLimit;

    @NotBlank(message = "Monthly fee can not be blank")
    @Column(nullable = false)
    private Double monthlyFee;

    @OneToMany(mappedBy = "coverage")
    private List<Subscription> subscriptions;
}
