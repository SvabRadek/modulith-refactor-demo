package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ApplicationModuleTest
@ActiveProfiles("test")
class DeleteContractUseCaseTest {

    @Autowired
    private DeleteContractUseCase deleteContractUseCase;

    @Autowired
    private CreateContractUseCase createContractUseCase;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void itCanDeleteContract() {
        var form = new CreateContractForm(new ContractRepresentations(Map.of("symbol", "AAPL")));
        var contract = createContractUseCase.handle(form);

        assertThat(contractRepository.findById(contract.id())).isPresent();

        deleteContractUseCase.handle(contract.id());

        assertThat(contractRepository.findById(contract.id())).isEmpty();
    }

    @Test
    void itThrowsExceptionWhenContractNotFound() {
        var form = new CreateContractForm(new ContractRepresentations(Map.of("symbol", "TSLA")));
        var contract = createContractUseCase.handle(form);

        deleteContractUseCase.handle(contract.id());

        assertThatThrownBy(() -> deleteContractUseCase.handle(contract.id()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found");
    }

    @Test
    void itCanDeleteMultipleContracts() {
        var contract1 = createContractUseCase.handle(
                new CreateContractForm(new ContractRepresentations(Map.of("symbol", "MSFT")))
        );
        var contract2 = createContractUseCase.handle(
                new CreateContractForm(new ContractRepresentations(Map.of("symbol", "GOOGL")))
        );

        deleteContractUseCase.handle(contract1.id());
        deleteContractUseCase.handle(contract2.id());

        assertThat(contractRepository.findById(contract1.id())).isEmpty();
        assertThat(contractRepository.findById(contract2.id())).isEmpty();
    }
}
