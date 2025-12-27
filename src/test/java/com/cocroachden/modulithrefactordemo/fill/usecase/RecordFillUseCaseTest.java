package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountForm;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountUseCase;
import com.cocroachden.modulithrefactordemo.account.usecase.FillAlreadyExistsException;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.fill.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.fill.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.event.ContractCreated;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractForm;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractUseCase;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
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
import java.util.UUID;

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
        var account = createAccountUseCase.handle(new CreateAccountForm(AccountName.of("TestAccount"), TradingEnvironment.SIM));
        var contract = createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("TT", "AAPL"))
        ));

        var form = new RecordFillForm(
                new ExchangeTradeId(UUID.randomUUID().toString()),
                new ExchangeOrderId(UUID.randomUUID().toString()),
                account.name(),
                account.tradingEnvironment(),
                List.of(new ContractRepresentation("TT", "AAPL")),
                new Price(15000L),
                new Qty(10L)
        );

        scenario.stimulate(() -> recordFillUseCase.handle(form))
                .forEventOfType(FillRecorded.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.fill()).isNotNull();
                    assertThat(event.fill().id()).isNotNull();
                    assertThat(event.fill().accountId()).isEqualTo(account.id());
                    assertThat(event.fill().contractId()).isEqualTo(contract.id());
                    assertThat(event.fill().price()).isEqualTo(new Price(15000L));
                    assertThat(event.fill().qty()).isEqualTo(new Qty(10L));
                });
    }

    @Test
    void itCreatesAccountAutomaticallyIfNotExists() {
        createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("symbol", "TSLA"))
        ));

        var form = new RecordFillForm(
                new ExchangeTradeId(UUID.randomUUID().toString()),
                new ExchangeOrderId(UUID.randomUUID().toString()),
                AccountName.of("AutoCreatedAccount"),
                TradingEnvironment.UAT,
                List.of(new ContractRepresentation("symbol", "TSLA")),
                new Price(20000L),
                new Qty(5L)
        );

        var recordedFill = recordFillUseCase.handle(form);

        assertThat(recordedFill).isNotNull();
        assertThat(accountRepository.findByNameAndTradingEnvironment(
                form.accountName(), form.tradingEnvironment()
        )).isPresent();
    }

    @Test
    void itCreatesNewWhenContractNotFound(Scenario scenario) {
        var account = createAccountUseCase.handle(
                new CreateAccountForm(AccountName.of("TestAccount2"), TradingEnvironment.SIM)
        );
        var form = new RecordFillForm(
                new ExchangeTradeId(UUID.randomUUID().toString()),
                new ExchangeOrderId(UUID.randomUUID().toString()),
                account.name(),
                account.tradingEnvironment(),
                List.of(new ContractRepresentation("symbol", "NONEXISTENT")),
                new Price(10000L),
                new Qty(1L)
        );
        scenario.stimulate(() -> recordFillUseCase.handle(form))
                .forEventOfType(ContractCreated.class)
                .toArriveAndVerify(e -> {
                    assertThat(e.getContract().representations().getRaw()).containsEntry("symbol", "NONEXISTENT");
                });
    }

    @Test
    void itThrowsExceptionWhenDuplicateFill() {
        var account = createAccountUseCase.handle(new CreateAccountForm(AccountName.of("TestAccount3"), TradingEnvironment.LIVE));
        createContractUseCase.handle(new CreateContractForm(
                new ContractRepresentations(Map.of("symbol", "MSFT"))
        ));

        var tradeId = new ExchangeTradeId(UUID.randomUUID().toString());
        var orderId = new ExchangeOrderId(UUID.randomUUID().toString());

        var form = new RecordFillForm(
                tradeId,
                orderId,
                account.name(),
                account.tradingEnvironment(),
                List.of(new ContractRepresentation("symbol", "MSFT")),
                new Price(30000L),
                new Qty(20L)
        );

        recordFillUseCase.handle(form);

        assertThatThrownBy(() -> recordFillUseCase.handle(form))
                .isInstanceOf(FillAlreadyExistsException.class);
    }
}
