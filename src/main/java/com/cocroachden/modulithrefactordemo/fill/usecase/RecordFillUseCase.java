package com.cocroachden.modulithrefactordemo.fill.usecase;

import com.cocroachden.modulithrefactordemo.account.query.AccountQuery;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountForm;
import com.cocroachden.modulithrefactordemo.account.usecase.CreateAccountUseCase;
import com.cocroachden.modulithrefactordemo.account.usecase.FillAlreadyExistsException;
import com.cocroachden.modulithrefactordemo.account.utils.AccountUtils;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.query.ContractQuery;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractForm;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractUseCase;
import com.cocroachden.modulithrefactordemo.fill.domain.FillId;
import com.cocroachden.modulithrefactordemo.fill.domain.RecordedFill;
import com.cocroachden.modulithrefactordemo.fill.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.fill.repository.FillEntity;
import com.cocroachden.modulithrefactordemo.fill.repository.FillRepository;
import com.cocroachden.modulithrefactordemo.fill.utils.FillUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.time.InstantSource;

@UseCase
@AllArgsConstructor
@Slf4j
public class RecordFillUseCase {

    private final FillRepository fillRepository;
    private final InstantSource instantSource;
    private final ContractQuery contractQuery;
    private final AccountQuery accountQuery;
    private final ApplicationEventPublisher publisher;
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateContractUseCase createContractUseCase;

    public RecordedFill handle(RecordFillForm form) throws FillAlreadyExistsException {
        log.info("Recording fill {}", form.tradeId());
        if (fillRepository.existsByTradeIdAndOrderId(form.tradeId(), form.orderId())) {
            throw new FillAlreadyExistsException(form.tradeId(), form.orderId());
        }
        var account = accountQuery.findByName(form.accountName(), form.tradingEnvironment())
                .orElseGet(() -> createAccountUseCase.handle(
                        new CreateAccountForm(
                                form.accountName(),
                                form.tradingEnvironment()
                        )
                ));
        var representations = new ContractRepresentations(form.representations());
        var contract = contractQuery.findContract(representations)
                .orElseGet(() -> createContractUseCase.handle(new CreateContractForm(representations)));
        var fillEntity = new FillEntity(FillId.random());
        fillEntity.setAccountId(account.id());
        fillEntity.setContractId(contract.id());
        fillEntity.setPrice(form.price());
        fillEntity.setQty(form.qty());
        fillEntity.setOrderId(form.orderId());
        fillEntity.setTradeId(form.tradeId());
        fillEntity.setRecordedAt(instantSource.instant());
        var saved = FillUtils.map(fillRepository.save(fillEntity));
        publisher.publishEvent(new FillRecorded(saved));
        return saved;
    }
}
