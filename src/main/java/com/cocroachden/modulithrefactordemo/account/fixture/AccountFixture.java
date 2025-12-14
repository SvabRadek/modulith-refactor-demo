package com.cocroachden.modulithrefactordemo.account.fixture;

import com.cocroachden.modulithrefactordemo.account.AccountId;
import com.cocroachden.modulithrefactordemo.account.OrderId;
import com.cocroachden.modulithrefactordemo.account.usecase.RecordFillForm;
import com.cocroachden.modulithrefactordemo.account.TradeId;
import com.cocroachden.modulithrefactordemo.account.usecase.RecordFillUseCase;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.infrastructure.fixture.Fixture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountFixture implements Fixture {

    private static final int FILL_COUNT = 3000;
    public static final String TAG = "account/basic";
    private final RecordFillUseCase recordFillUseCase;

    @Override
    public void execute() {
        log.info("Generating [BASIC] account fixture...");
        var start = System.currentTimeMillis();
        var accounts = List.of(
                new AccountId("A", TradingEnvironment.SIM),
                new AccountId("B", TradingEnvironment.UAT),
                new AccountId("C", TradingEnvironment.LIVE),
                new AccountId("D", TradingEnvironment.UAT),
                new AccountId("E", TradingEnvironment.SIM)
        );

        for (int i = 0; i < FILL_COUNT; i++) {
            recordFillUseCase.handle(
                    new RecordFillForm(
                            TradeId.random(),
                            OrderId.random(),
                            accounts.get(i % accounts.size()),
                            List.of(
                                    new ContractRepresentation("A", "A" + i),
                                    new ContractRepresentation("B", "B" + i),
                                    new ContractRepresentation("C", "C" + i)
                            ),
                            1L,
                            1L
                    )
            );
        }
        log.info("Account fixture completed!");
        log.info("Duration: {}s", Duration.ofMillis(System.currentTimeMillis() - start).toSeconds());
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
