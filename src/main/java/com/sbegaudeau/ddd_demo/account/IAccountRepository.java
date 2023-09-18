package com.sbegaudeau.ddd_demo.account;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
