package com.ideas2it.workshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;
}
