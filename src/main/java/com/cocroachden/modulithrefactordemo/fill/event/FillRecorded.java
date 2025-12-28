package com.cocroachden.modulithrefactordemo.fill.event;

import com.cocroachden.modulithrefactordemo.fill.RecordedFill;
import org.jmolecules.event.annotation.DomainEvent;

@DomainEvent
public record FillRecorded(RecordedFill fill) {
}
