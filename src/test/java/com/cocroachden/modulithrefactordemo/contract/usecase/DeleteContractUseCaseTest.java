package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.event.ContractDeleted;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
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

    @BeforeEach
    public void setup() {
        contractRepository.deleteAll();
    }

    @Test
    void itCanDeleteContract(Scenario scenario) {
        var form = new CreateContractForm(new ContractRepresentations(Map.of("symbol", "AAPL")));
        var contract = createContractUseCase.handle(form);
        scenario.stimulate(() -> deleteContractUseCase.handle(contract.id()))
                .forEventOfType(ContractDeleted.class)
                .toArriveAndVerify(event -> {
                    assertThat(contractRepository.existsById(contract.id())).isFalse();
                });
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
        contractRepository.flush();
        assertThat(contractRepository.findById(contract1.id())).isEmpty();
        assertThat(contractRepository.findById(contract2.id())).isEmpty();
    }
}
