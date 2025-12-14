package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.AccountId;
import com.cocroachden.modulithrefactordemo.account.domain.OrderId;
import com.cocroachden.modulithrefactordemo.account.domain.TradeId;
import com.cocroachden.modulithrefactordemo.account.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.account.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractForm;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractUseCase;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.InstantSource;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@ActiveProfiles("test")
class RecordFillUseCaseTest {

    @MockitoBean
    private InstantSource instantSource;

    @Autowired
    private RecordFillUseCase recordFillUseCase;

    @Autowired
    private CreateAccountUseCase createAccountUseCase;

    @Autowired
    private CreateContractUseCase createContractUseCase;

    @Autowired
    private FillRepository fillRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        fillRepository.deleteAll();
        contractRepository.deleteAll();
    }

    @Test
    void itCanRecordFill(Scenario scenario) {
        var accountId = new AccountId("TestAccount", TradingEnvironment.SIM);
        createAccountUseCase.handle(new CreateAccountForm("TestAccount", TradingEnvironment.SIM));
        var contract = createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("TT", "AAPL"))
        ));

        var form = new RecordFillForm(
                TradeId.random(),
                OrderId.random(),
                accountId,
                List.of(new ContractRepresentation("TT", "AAPL")),
                15000L,
                10L
        );

        scenario.stimulate(() -> recordFillUseCase.handle(form))
                .forEventOfType(FillRecorded.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.fill()).isNotNull();
                    assertThat(event.fill().id()).isNotNull();
                    assertThat(event.fill().accountId()).isEqualTo(accountId);
                    assertThat(event.fill().contractId()).isEqualTo(contract.id());
                    assertThat(event.fill().price()).isEqualTo(15000L);
                    assertThat(event.fill().qty()).isEqualTo(10L);
                });
    }

    @Test
    void itCreatesAccountAutomaticallyIfNotExists() {
        var accountId = new AccountId("AutoCreatedAccount", TradingEnvironment.UAT);

        var contract = createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("symbol", "TSLA"))
        ));

        var form = new RecordFillForm(
                TradeId.random(),
                OrderId.random(),
                accountId,
                List.of(new ContractRepresentation("symbol", "TSLA")),
                20000L,
                5L
        );

        var recordedFill = recordFillUseCase.handle(form);

        assertThat(recordedFill).isNotNull();
        assertThat(accountRepository.findById(accountId)).isPresent();
    }

    @Test
    void itThrowsExceptionWhenContractNotFound() {
        var accountId = new AccountId("TestAccount2", TradingEnvironment.SIM);
        createAccountUseCase.handle(new CreateAccountForm("TestAccount2", TradingEnvironment.SIM));

        var form = new RecordFillForm(
                TradeId.random(),
                OrderId.random(),
                accountId,
                List.of(new ContractRepresentation("symbol", "NONEXISTENT")),
                10000L,
                1L
        );

        assertThatThrownBy(() -> recordFillUseCase.handle(form))
                .isInstanceOf(ContractNotFoundException.class);
    }

    @Test
    void itThrowsExceptionWhenDuplicateFill() {
        var accountId = new AccountId("TestAccount3", TradingEnvironment.LIVE);
        createAccountUseCase.handle(new CreateAccountForm("TestAccount3", TradingEnvironment.LIVE));

        var contract = createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("symbol", "MSFT"))
        ));

        var tradeId = TradeId.random();
        var orderId = OrderId.random();

        var form = new RecordFillForm(
                tradeId,
                orderId,
                accountId,
                List.of(new ContractRepresentation("symbol", "MSFT")),
                30000L,
                20L
        );

        recordFillUseCase.handle(form);

        assertThatThrownBy(() -> recordFillUseCase.handle(form))
                .isInstanceOf(FillAlreadyExistsException.class);
    }
}
