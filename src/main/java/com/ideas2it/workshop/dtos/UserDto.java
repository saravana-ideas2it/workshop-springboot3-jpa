package com.ideas2it.workshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank
    @JsonProperty("emailAddress")
    private String emailAddress;

    @NotBlank
    @JsonProperty("password")
    private String password;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("phone")
    private String phone;
}
