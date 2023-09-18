package com.sbegaudeau.ddd_demo.account;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<Account, UUID> {
    Optional<Account> findByUsername(String username);
}
