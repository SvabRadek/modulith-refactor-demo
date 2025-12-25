package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class ContractEntity extends AbstractEntity<ContractId> {

    @EmbeddedId
    private ContractId id;

    public ContractEntity(ContractId id) {
        this.id = id;
    }

    @ElementCollection
    @CollectionTable(
            name = "contract_representation",
            joinColumns = @JoinColumn(name = "contract_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"contract_id", "format"}),
                    @UniqueConstraint(columnNames = {"format", "representation"})
            }
    )
    private Set<ContractRepresentationEntity> representations = new HashSet<>();

    public void setRepresentations(Set<ContractRepresentationEntity> representations) {
        this.representations.clear();
        this.representations.addAll(representations);
    }

    public void setRepresentations(ContractRepresentations representations) {
        this.representations.clear();
        var set = representations.stream()
                .map(ContractUtils::map)
                .collect(Collectors.toSet());
        this.representations.addAll(set);
    }
}
