package com.ths.restapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryData {

    private String id;
    @NotEmpty(message = "Name is required")
    private String name;
}
