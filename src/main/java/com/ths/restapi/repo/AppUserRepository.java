package com.ths.restapi.repo;

import com.ths.restapi.entity.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends PagingAndSortingRepository<AppUser,Long> {

    Optional<AppUser> findByEmail(String email);

}
