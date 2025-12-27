package com.cocroachden.modulithrefactordemo.fill.fixture;

import com.cocroachden.modulithrefactordemo.account.domain.*;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeOrderId;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Price;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.Qty;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.ExchangeTradeId;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillForm;
import com.cocroachden.modulithrefactordemo.fill.usecase.RecordFillUseCase;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import com.cocroachden.modulithrefactordemo.infrastructure.fixture.Fixture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class FillFixture implements Fixture {

    private static final int FILL_COUNT = 3000;
    public static final String TAG = "fill/basic";
    private final RecordFillUseCase recordFillUseCase;

    @Override
    public void execute() {
        log.info("Generating [BASIC] fill fixture...");
        var start = System.currentTimeMillis();
        var accounts = List.of(
                new Account(AccountId.random(), new AccountName("A"), TradingEnvironment.SIM),
                new Account(AccountId.random(), new AccountName("B"), TradingEnvironment.UAT),
                new Account(AccountId.random(), new AccountName("C"), TradingEnvironment.LIVE),
                new Account(AccountId.random(), new AccountName("D"), TradingEnvironment.UAT),
                new Account(AccountId.random(), new AccountName("E"), TradingEnvironment.SIM)
        );

        for (int i = 0; i < FILL_COUNT; i++) {
            recordFillUseCase.handle(
                    new RecordFillForm(
                            new ExchangeTradeId(UUID.randomUUID().toString()),
                            new ExchangeOrderId(UUID.randomUUID().toString()),
                            accounts.get(i % accounts.size()).name(),
                            accounts.get(i % accounts.size()).tradingEnvironment(),
                            List.of(
                                    new ContractRepresentation("A", "A" + i),
                                    new ContractRepresentation("B", "B" + i),
                                    new ContractRepresentation("C", "C" + i)
                            ),
                            new Price(1L),
                            new Qty(1L)
                    )
            );
        }
        log.info("Fill fixture completed!");
        log.info("Duration: {}s", Duration.ofMillis(System.currentTimeMillis() - start).toSeconds());
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
