package com.app.nailcare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Coverage limit can not be blank")
    @Column(nullable = false)
    private Integer coverageLimit;

    @NotNull(message = "Monthly fee can not be blank")
    @Column(nullable = false)
    private Double monthlyFee;

    @OneToMany
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Subscription> subscriptions;
}
