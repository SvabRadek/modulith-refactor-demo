package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.AccountName;
import com.cocroachden.modulithrefactordemo.account.event.AccountCreated;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.InstantSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@ActiveProfiles("test")
class CreateAccountUseCaseTest {

    @MockitoBean
    private InstantSource instantSource;

    @Autowired
    private CreateAccountUseCase createAccountUseCase;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void itCanCreateAccount(Scenario scenario) {
        var command = new CreateAccountCommand(AccountName.of("MyAccount"), TradingEnvironment.SIM, null);
        scenario.stimulate(() -> createAccountUseCase.handle(command))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount()).isNotNull();
                    assertThat(event.getAccount().name()).isEqualTo(command.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(TradingEnvironment.SIM);
                });
    }

    @Test
    void itSavesAccountToDatabase(Scenario scenario) {
        var command = new CreateAccountCommand(AccountName.of("PersistentAccount"), TradingEnvironment.UAT, null);
        scenario.stimulate(() -> createAccountUseCase.handle(command))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    var savedAccount = accountRepository.findById(event.getAccount().id());
                    assertThat(savedAccount).isPresent();
                    assertThat(savedAccount.get().getName()).isEqualTo(command.name());
                });
    }

    @Test
    void itThrowsExceptionWhenAccountAlreadyExists() {
        var command = new CreateAccountCommand(AccountName.of("DuplicateAccount"), TradingEnvironment.LIVE, null);
        createAccountUseCase.handle(command);
        assertThatThrownBy(() -> createAccountUseCase.handle(command))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Account [DuplicateAccount:LIVE] already exists!");
    }

    @Test
    void itCanCreateAccountsWithDifferentEnvironments(Scenario scenario) {
        var commandSim = new CreateAccountCommand(AccountName.of("MultiEnvAccount"), TradingEnvironment.SIM, null);
        var commandUat = new CreateAccountCommand(AccountName.of("MultiEnvAccount"), TradingEnvironment.UAT, null);
        scenario.stimulate(() -> createAccountUseCase.handle(commandSim))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount().name()).isEqualTo(commandSim.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(commandSim.tradingEnvironment());
                });
        scenario.stimulate(() -> createAccountUseCase.handle(commandUat))
                .forEventOfType(AccountCreated.class)
                .matching(e -> e.getAccount().tradingEnvironment().equals(TradingEnvironment.UAT))
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount().name()).isEqualTo(commandUat.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(TradingEnvironment.UAT);
                });
    }
}
