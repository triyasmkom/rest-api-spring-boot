package com.ths.restapi.controller;

import com.ths.restapi.dto.AppUserData;
import com.ths.restapi.dto.ResponseData;
import com.ths.restapi.entity.AppUser;
import com.ths.restapi.service.AppUserSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    @Autowired private AppUserSevice appUserSevice;
    @Autowired private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData appUserData){
        ResponseData<AppUser> responseData = new ResponseData<>();
        AppUser appUser = modelMapper.map(appUserData, AppUser.class);
        responseData.setPayload(appUserSevice.registerAppUser(appUser));
        responseData.setStatus(true);
        responseData.getMessage().add("App User Save!");

        return ResponseEntity.ok(responseData);
    }
}
