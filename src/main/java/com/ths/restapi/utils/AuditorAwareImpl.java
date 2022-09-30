package com.ths.restapi.utils;

import com.ths.restapi.entity.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

// class ini digunakan untuk memberitahu JPA siapa yang melakukan proses create dan update
public class AuditorAwareImpl implements org.springframework.data.domain.AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // untuk mendapatkan current user yg saat ini login
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }
}
