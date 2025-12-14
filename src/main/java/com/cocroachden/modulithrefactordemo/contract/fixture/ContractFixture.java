package com.cocroachden.modulithrefactordemo.contract.fixture;

import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentation;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractForm;
import com.cocroachden.modulithrefactordemo.contract.query.ContractQuery;
import com.cocroachden.modulithrefactordemo.contract.usecase.CreateContractUseCase;
import com.cocroachden.modulithrefactordemo.infrastructure.fixture.Fixture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
@Slf4j
public class ContractFixture implements Fixture {

    public static final int CONTRACT_NUM = 16000;
    public static final String TAG = "contract/basic";
    private final CreateContractUseCase createContractUseCase;
    private final ContractQuery contractQuery;

    @Override
    public void execute() {
        log.info("Generating [BASIC] contract fixture...");
        var start = System.currentTimeMillis();
        for (int i = 0; i < CONTRACT_NUM; i++) {
            ContractRepresentation a = new ContractRepresentation("A", "A" + i);
            ContractRepresentation b = new ContractRepresentation("B", "B" + i);
            ContractRepresentation c = new ContractRepresentation("C", "C" + i);
            var exists = contractQuery.findContract(a);
            if (exists.isEmpty()) {
                exists = contractQuery.findContract(b);
            }
            if (exists.isEmpty()) {
                exists = contractQuery.findContract(c);
            }
            if (exists.isEmpty()) {
                createContractUseCase.handle(
                        new CreateContractForm(new ContractRepresentations(a, b, c))
                );
            }
        }
        log.info("Contract fixture completed!");
        log.info("Duration: {}s", Duration.ofMillis(System.currentTimeMillis() - start).toSeconds());
    }

    @Override
    public String getTag() {
        return TAG;
    }
}
