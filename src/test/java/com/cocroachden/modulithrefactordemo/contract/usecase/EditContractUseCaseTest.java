package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ApplicationModuleTest
@ActiveProfiles("test")
class EditContractUseCaseTest {

    @Autowired
    private EditContractUseCase editContractUseCase;

    @Autowired
    private CreateContractUseCase createContractUseCase;

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        contractRepository.deleteAll();
    }

    @Test
    void itCanEditContract() {
        var createForm = new CreateContractForm(new ContractRepresentations(Map.of("symbol", "AAPL")));
        var contract = createContractUseCase.handle(createForm);

        var editForm = new EditContractForm(
                contract.id(),
                new ContractRepresentations(Map.of("symbol", "TSLA", "exchange", "NASDAQ"))
        );
        var editedContract = editContractUseCase.handle(editForm);

        assertThat(editedContract.id()).isEqualTo(contract.id());
        assertThat(editedContract.representations().getRaw())
                .hasSize(2)
                .containsEntry("symbol", "TSLA")
                .containsEntry("exchange", "NASDAQ");
    }

    @Test
    @Transactional
    void itUpdatesContractInDatabase() {
        var createForm = new CreateContractForm(new ContractRepresentations(Map.of("type", "STOCK")));
        var contract = createContractUseCase.handle(createForm);

        var editForm = new EditContractForm(
                contract.id(),
                new ContractRepresentations(Map.of("type", "OPTION", "strike", "150"))
        );
        editContractUseCase.handle(editForm);

        var savedContract = contractRepository.findById(contract.id());
        assertThat(savedContract).isPresent();
        assertThat(ContractUtils.map(savedContract.get().getRepresentations()).getRaw())
                .containsEntry("type", "OPTION")
                .containsEntry("strike", "150");
    }

    @Test
    void itThrowsExceptionWhenContractNotFound() {
        var editForm = new EditContractForm(
                ContractId.random(),
                new ContractRepresentations(Map.of("symbol", "GOOGL"))
        );
        assertThatThrownBy(() -> editContractUseCase.handle(editForm))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not found!");
    }

    @Test
    void itCanReplaceAllRepresentations() {
        var createForm = new CreateContractForm(new ContractRepresentations(Map.of(
                "symbol", "AMD",
                "exchange", "NASDAQ",
                "type", "STOCK"
        )));
        var contract = createContractUseCase.handle(createForm);

        var editForm = new EditContractForm(
                contract.id(),
                new ContractRepresentations(Map.of("newField", "newValue"))
        );
        var editedContract = editContractUseCase.handle(editForm);

        assertThat(editedContract.representations().getRaw())
                .hasSize(1)
                .containsEntry("newField", "newValue")
                .doesNotContainKey("symbol")
                .doesNotContainKey("exchange")
                .doesNotContainKey("type");
    }
}
