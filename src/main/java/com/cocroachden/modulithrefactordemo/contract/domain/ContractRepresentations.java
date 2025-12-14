package com.cocroachden.modulithrefactordemo.contract.domain;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@AllArgsConstructor
public class ContractRepresentations {
    private Map<String, String> representations;

    public ContractRepresentations() {
        this.representations = new HashMap<>();
    }

    public ContractRepresentations(List<ContractRepresentation> representations) {
        this.representations = new HashMap<>();
        representations.forEach(r -> this.representations.put(r.format(), r.value()));
    }

    public ContractRepresentations(ContractRepresentation... representations) {
        this.representations = new HashMap<>();
        for (ContractRepresentation representation : representations) {
            this.representations.put(representation.format(), representation.value());
        }
    }

    public Stream<ContractRepresentation> stream() {
        return this.representations.entrySet()
                .stream()
                .map(e -> new ContractRepresentation(e.getKey(), e.getValue()));
    }

    public Map<String, String> getRaw() {
        return representations;
    }

    public List<ContractRepresentation> toList() {
        return this.stream().toList();
    }

    public ContractRepresentations putAll(List<ContractRepresentation> representations) {
        representations.forEach(this::put);
        return this;
    }

    public ContractRepresentations putAll(ContractRepresentations representations) {
        representations.stream().forEach(this::put);
        return this;
    }

    public ContractRepresentations put(ContractRepresentation representation) {
        representations.put(representation.format(), representation.value());
        return this;
    }

    public ContractRepresentations put(String format, String value) {
        representations.put(format, value);
        return this;
    }

    public List<ContractRepresentation> getRepresentationsByUsage() {
        return representations.entrySet()
                .stream()
                .map(e -> new ContractRepresentation(e.getKey(), e.getValue()))
                .toList();
    }
}
