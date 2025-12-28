package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.event.ContractCreated;
import com.cocroachden.modulithrefactordemo.contract.event.ContractDeleted;
import com.cocroachden.modulithrefactordemo.contract.event.ContractEdited;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AggregateRoot
public class ContractEntity extends AbstractEntity<ContractId> {

    @Identity
    @EmbeddedId
    private ContractId id;

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

    private ContractEntity(ContractId id) {
        this.id = id;
    }

    public static ContractEntity create(ContractId id, ContractRepresentations representations) {
        var entity = new ContractEntity(id);
        entity.representations = representations.stream().map(ContractUtils::map).collect(Collectors.toSet());
        entity.registerEvent(new ContractCreated(ContractUtils.map(entity)));
        return entity;
    }

    public ContractEntity editRepresentations(ContractRepresentations representations) {
        this.representations.clear();
        var set = representations.stream()
                .map(ContractUtils::map)
                .collect(Collectors.toSet());
        this.representations.addAll(set);
        registerEvent(new ContractEdited(ContractUtils.map(this)));
        return this;
    }

    @PreRemove
    protected void onRemove() {
        registerEvent(new ContractDeleted(this.id));
    }

    @Override
    public ContractId getId() {
        return id;
    }

    public Set<ContractRepresentationEntity> getRepresentations() {
        return Set.copyOf(representations);
    }
}
