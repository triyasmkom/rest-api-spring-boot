package com.ths.restapi.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseData <T>{
    private boolean status;
    private List message = new ArrayList<>();
    private T payload;
}
