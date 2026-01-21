package org.acme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
    @NotBlank
    public String name;

    @NotBlank
    public String nationalId;

    @Email
    public String email;

    @NotBlank
    public String status;
}
