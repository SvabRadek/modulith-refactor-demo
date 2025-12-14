package com.cocroachden.modulithrefactordemo.contract.query;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContractQuery {

    private final ContractRepository repository;

    @Transactional
    public Optional<Contract> findContract(ContractId id) {
        return repository.findById(id).map(ContractUtils::map);
    }

    @Transactional
    public Optional<Contract> findContract(String format, String value) {
        return repository.findByRepresentation(format, value).map(ContractUtils::map);
    }

    @Transactional
    public Optional<Contract> findContract(ContractRepresentation representation) {
        return this.findContract(representation.format(), representation.value());
    }

    @Transactional
    public Optional<Contract> findContract(ContractRepresentations representations) {
        for (var representation : representations.getRepresentationsByUsage()) {
            var found = this.findContract(representation);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }
}
