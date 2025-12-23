package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
            indexes = @Index(name = "idx_format_repre", columnList = "format, representation"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"format", "representation"}),
                    @UniqueConstraint(columnNames = {"contract_id", "format"})
            }
    )
    private Set<ContractRepresentationEntity> representations = new HashSet<>();

    public void setRepresentations(ContractRepresentations representations) {
        this.representations.clear();
        this.representations.addAll(ContractUtils.map(representations));
    }

    public ContractRepresentations getRepresentations() {
        return new ContractRepresentations(
                representations.stream()
                        .map(ContractUtils::map)
                        .toList()
        );
    }
}
