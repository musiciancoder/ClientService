package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer extends PanacheEntity {
    @NotBlank public String name;
    @NotBlank public String nationalId;
    @Email public String email;
    @NotBlank public String status; // ACTIVE | INACTIVE
}