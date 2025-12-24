package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.event.ContractCreated;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ActiveProfiles("test")
class CreateContractUseCaseTest {

    @Autowired
    private CreateContractUseCase createContractUseCase;

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        contractRepository.deleteAll();
    }

    @Test
    void itCanCreateContract(Scenario scenario) {
        var form = new CreateContractForm(new ContractRepresentations(Map.of("format", "value")));
        scenario.stimulate(() -> createContractUseCase.handle(form))
                .forEventOfType(ContractCreated.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.getContract().representations().getRaw()).containsEntry("format", "value");
                    assertThat(event.getContract().id()).isNotNull();
                });
    }

    @Test
    @Transactional
    void itSavesContractToDatabase() {
        var form = new CreateContractForm(new ContractRepresentations(Map.of("symbol", "AAPL")));
        var contract = createContractUseCase.handle(form);

        var contractFromDb = contractRepository.findById(contract.id());
        assertThat(contractFromDb).isPresent();
        assertThat(contractFromDb.get().getRepresentations().getRaw()).containsEntry("symbol", "AAPL");
    }

    @Test
    @Transactional
    void itCanCreateContractWithMultipleRepresentations(Scenario scenario) {
        var form = new CreateContractForm(new ContractRepresentations(Map.of(
                "symbol", "TSLA",
                "exchange", "NASDAQ",
                "type", "STOCK"
        )));
        scenario.stimulate(() -> createContractUseCase.handle(form))
                .forEventOfType(ContractCreated.class)
                .toArriveAndVerify(event -> {
                    var contractFromDb = contractRepository.findById(event.getContract().id());
                    if (contractFromDb.isEmpty()) {
                        Assertions.fail("Contract not found");
                    } else {
                        assertThat(contractFromDb.get().getRepresentations().getRaw())
                                .hasSize(3)
                                .containsEntry("symbol", "TSLA")
                                .containsEntry("exchange", "NASDAQ")
                                .containsEntry("type", "STOCK");
                    }
                });


    }
}
