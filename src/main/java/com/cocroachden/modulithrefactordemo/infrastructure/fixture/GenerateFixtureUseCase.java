package com.cocroachden.modulithrefactordemo.infrastructure.fixture;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenerateFixtureUseCase {

    private final List<Fixture> fixtures;
    private final FixtureConfiguration fixtureConfiguration;

    @EventListener(ApplicationStartedEvent.class)
    public void generate() {
        if (!fixtureConfiguration.getEnabled()) {
            return;
        }
        fixtureConfiguration.getTags()
                .forEach(t -> fixtures.stream()
                        .filter(f -> f.getTag().equals(t)).findAny()
                        .ifPresent(Fixture::execute)
                );
    }
}
