package com.sbegaudeau.ddd_demo.email;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmailEntryRepository extends CrudRepository<EmailEntry, UUID> {
}
