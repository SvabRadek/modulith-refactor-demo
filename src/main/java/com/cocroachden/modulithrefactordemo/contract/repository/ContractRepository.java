package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface ContractRepository extends JpaRepository<ContractEntity, ContractId> {

    @Transactional(readOnly = true)
    @Query("select c from ContractEntity c join c.representations cr where cr.format = :format and cr.representation = :value")
    Optional<ContractEntity> findByRepresentation(String format, String value);

    @Transactional(readOnly = true)
    @Query("select c from ContractEntity c join c.representations cr where cr in :representations")
    Optional<ContractEntity> findByRepresentations(Set<ContractRepresentationEntity> representations);
}
