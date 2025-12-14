package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<ContractEntity, ContractId> {

    @Transactional(readOnly = true)
    @Query("select c from ContractEntity c join c.representations r where key(r) = :format and value(r) = :value")
    Optional<ContractEntity> findByRepresentation(String format, String value);

//    Optional<ContractEntity> findById(ContractId id);

    @Transactional(readOnly = true)
    default Optional<ContractEntity> findByRepresentations(ContractRepresentations representations) {
        for (var representation : representations.getRepresentationsByUsage()) {
            var found = this.findByRepresentation(representation.format(), representation.value());
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }

}
