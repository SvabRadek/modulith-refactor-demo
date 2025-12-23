package com.cocroachden.modulithrefactordemo.account.usecase;

import com.cocroachden.modulithrefactordemo.account.domain.FillId;
import com.cocroachden.modulithrefactordemo.account.domain.RecordedFill;
import com.cocroachden.modulithrefactordemo.account.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.account.repository.AccountRepository;
import com.cocroachden.modulithrefactordemo.account.repository.FillEntity;
import com.cocroachden.modulithrefactordemo.account.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.query.ContractQuery;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.time.InstantSource;

@UseCase
@AllArgsConstructor
public class RecordFillUseCase {

    private final FillRepository fillRepository;
    private final AccountRepository accountRepository;
    private final InstantSource instantSource;
    private final ContractQuery contractQuery;
    private final ApplicationEventPublisher publisher;
    private final CreateAccountUseCase createAccountUseCase;

    public RecordedFill handle(RecordFillForm form) throws FillAlreadyExistsException, ContractNotFoundException {
        if (fillRepository.existsByTradeIdAndOrderId(form.tradeId(), form.orderId())) {
            throw new FillAlreadyExistsException(form.tradeId(), form.orderId());
        }
        var account = accountRepository.findById(form.accountId())
                .orElseGet(() -> {
                    var created = createAccountUseCase.handle(new CreateAccountForm(
                            form.accountId().name(),
                            form.accountId().tradingEnvironment()
                    ));
                    return accountRepository.findById(created.id()).orElseThrow();
                });
        var contract = contractQuery.findContract(new ContractRepresentations(form.representations()))
                .orElseThrow(() -> new ContractNotFoundException(form.tradeId(), form.orderId(), form.representations()));

        var fillEntity = new FillEntity(FillId.random());
        fillEntity.setAccount(account);
        fillEntity.setContractId(contract.id());
        fillEntity.setPrice(form.price());
        fillEntity.setQty(form.qty());
        fillEntity.setOrderId(form.orderId());
        fillEntity.setTradeId(form.tradeId());
        fillEntity.setRecordedAt(instantSource.instant());
        var saved = AccountUtils.map(fillRepository.save(fillEntity));
        publisher.publishEvent(new FillRecorded(saved));
        return saved;
    }

}
