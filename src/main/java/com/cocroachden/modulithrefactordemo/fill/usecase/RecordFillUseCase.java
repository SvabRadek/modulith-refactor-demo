package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.account.query.AccountQuery;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountCommand;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountUseCase;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.query.ContractQuery;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractCommand;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractUseCase;
import com.cocroachden.modulithrefactordemo.fill.RecordedFill;
import com.cocroachden.modulithrefactordemo.fill.repository.FillEntity;
import com.cocroachden.modulithrefactordemo.fill.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.fill.utils.FillUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.InstantSource;

@UseCase
@AllArgsConstructor
@Slf4j
public class RecordFillUseCase {

    private final FillRepository fillRepository;
    private final InstantSource instantSource;
    private final ContractQuery contractQuery;
    private final AccountQuery accountQuery;
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateContractUseCase createContractUseCase;

    public RecordedFill handle(RecordFillCommand command) throws FillAlreadyExistsException {
        log.info("Recording fill {}", command.tradeId());
        if (fillRepository.existsByTradeIdAndOrderId(command.tradeId(), command.orderId())) {
            throw new FillAlreadyExistsException(command.tradeId(), command.orderId());
        }
        var account = accountQuery.findByName(command.accountName(), command.tradingEnvironment())
                .orElseGet(() -> createAccountUseCase.handle(
                        new CreateAccountCommand(
                                command.accountName(),
                                command.tradingEnvironment()
                        )
                ));
        var representations = new ContractRepresentations(command.representations());
        var contract = contractQuery.findContract(representations)
                .orElseGet(() -> createContractUseCase.handle(new CreateContractCommand(representations)));
        var newFill = FillEntity.record(
                account.id(),
                command.tradeId(),
                command.orderId(),
                contract.id(),
                command.price(),
                command.qty(),
                instantSource.instant()
        );
        var saved = fillRepository.save(newFill);
        return FillUtils.map(saved);
    }
}
