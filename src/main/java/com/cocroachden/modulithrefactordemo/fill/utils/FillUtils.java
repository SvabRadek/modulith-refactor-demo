package com.cocroachden.modulithrefactordemo.fill.utils;

import com.cocroachden.modulithrefactordemo.fill.RecordedFill;
import com.cocroachden.modulithrefactordemo.fill.repository.FillEntity;

public class FillUtils {

    public static RecordedFill map(FillEntity entity) {
        return new RecordedFill(
                entity.getId(),
                entity.getAccountId(),
                entity.getContractId(),
                entity.getPrice(),
                entity.getQty(),
                entity.getRecordedAt()
        );
    }

}
