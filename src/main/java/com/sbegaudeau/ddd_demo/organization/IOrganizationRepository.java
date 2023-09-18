package com.sbegaudeau.ddd_demo.organization;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationRepository extends CrudRepository<Organization, UUID> {
}
