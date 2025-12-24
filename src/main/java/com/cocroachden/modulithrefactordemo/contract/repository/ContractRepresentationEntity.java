package com.cocroachden.modulithrefactordemo.contract.repository;

import com.cocroachden.modulithrefactordemo.infrastructure.repository.AbstractEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "contract_representation")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ContractRepresentationEntity extends AbstractEntity<ContractRepresentationEntity.Key> {

    @EmbeddedId
    private Key key;

    public ContractRepresentationEntity(Key key) {
        this.key = key;
    }

    @Override
    public Key getId() {
        return key;
    }

    @ManyToOne
    private ContractEntity contract;

    @Embeddable
    public record Key(String format, String representation) { }

}