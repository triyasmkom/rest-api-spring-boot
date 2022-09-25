package com.ths.restapi.dto;

import lombok.Data;

@Data
public class AppUserData {
    private String fullname;
    private String email;
    private String password;
    private String appUserRole;
}
