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
        var form = new CreateAccountForm(AccountName.of("MyAccount"), TradingEnvironment.SIM);
        scenario.stimulate(() -> createAccountUseCase.handle(form))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount()).isNotNull();
                    assertThat(event.getAccount().name()).isEqualTo(form.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(TradingEnvironment.SIM);
                });
    }

    @Test
    void itSavesAccountToDatabase(Scenario scenario) {
        var form = new CreateAccountForm(AccountName.of("PersistentAccount"), TradingEnvironment.UAT);
        scenario.stimulate(() -> createAccountUseCase.handle(form))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    var savedAccount = accountRepository.findById(event.getAccount().id());
                    assertThat(savedAccount).isPresent();
                    assertThat(savedAccount.get().getName()).isEqualTo(form.name());
                });
    }

    @Test
    void itThrowsExceptionWhenAccountAlreadyExists() {
        var form = new CreateAccountForm(AccountName.of("DuplicateAccount"), TradingEnvironment.LIVE);
        createAccountUseCase.handle(form);
        assertThatThrownBy(() -> createAccountUseCase.handle(form))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Account [DuplicateAccount:LIVE] already exists!");
    }

    @Test
    void itCanCreateAccountsWithDifferentEnvironments(Scenario scenario) {
        var formSim = new CreateAccountForm(AccountName.of("MultiEnvAccount"), TradingEnvironment.SIM);
        var formUat = new CreateAccountForm(AccountName.of("MultiEnvAccount"), TradingEnvironment.UAT);
        scenario.stimulate(() -> createAccountUseCase.handle(formSim))
                .forEventOfType(AccountCreated.class)
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount().name()).isEqualTo(formSim.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(formSim.tradingEnvironment());
                });
        scenario.stimulate(() -> createAccountUseCase.handle(formUat))
                .forEventOfType(AccountCreated.class)
                .matching(e -> e.getAccount().tradingEnvironment().equals(TradingEnvironment.UAT))
                .toArriveAndVerify(event -> {
                    assertThat(event.getAccount().name()).isEqualTo(formUat.name());
                    assertThat(event.getAccount().tradingEnvironment()).isEqualTo(TradingEnvironment.UAT);
                });
    }
}
