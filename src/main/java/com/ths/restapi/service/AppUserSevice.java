package com.ths.restapi.service;

import com.ths.restapi.entity.AppUser;
import com.ths.restapi.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserSevice implements UserDetailsService {

    @Autowired private AppUserRepository appUserRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("user with email '%s' not found", email)));
    }

    public AppUser registerAppUser(AppUser user){
        // validasi email duplicate
        boolean userExist = appUserRepository.findByEmail(user.getEmail()).isPresent();
        if (userExist){
            throw new RuntimeException(String.format("User with email '%s' is already exist", user.getEmail()));
        }

        // encode password bcrypt
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        return appUserRepository.save(user);
    }
}
