package com.cocroachden.modulithrefactordemo.contract.query;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ContractQuery {

    private final ContractRepository repository;

    @Transactional(readOnly = true)
    public Optional<Contract> findContract(ContractId id) {
        return repository.findById(id).map(ContractUtils::map);
    }

    @Transactional(readOnly = true)
    public Optional<Contract> findContract(String format, String value) {
        return repository.findByRepresentation(format, value).map(ContractUtils::map);
    }

    @Transactional(readOnly = true)
    public Optional<Contract> findContract(ContractRepresentation representation) {
        return this.findContract(representation.format(), representation.value());
    }

    @Transactional(readOnly = true)
    public Optional<Contract> findContract(ContractRepresentations representations) {
        return repository.findByRepresentations(ContractUtils.map(representations))
                .map(ContractUtils::map);
    }
}
