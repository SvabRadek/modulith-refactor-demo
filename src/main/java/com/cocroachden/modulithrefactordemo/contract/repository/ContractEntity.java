package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            uniqueConstraints = @UniqueConstraint(columnNames = {"format", "representation"})
    )
    @MapKeyColumn(name = "format")
    @Column(name = "representation")
    private Map<String, String> representations = new HashMap<>();

    public void setRepresentations(List<ContractRepresentation> representations) {
        this.representations = new HashMap<>();
        representations.forEach(r -> this.representations.put(r.format(), r.value()));
    }

    public void setRepresentations(ContractRepresentations representations) {
        this.representations = representations.getRepresentations();
    }

    public void setRepresentations(Map<String, String> representations) {
        this.representations = representations;
    }

    public ContractRepresentations getRepresentations() {
        return new ContractRepresentations(representations);
    }
}
