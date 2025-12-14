package com.cocroachden.modulithrefactordemo.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.InstantSource;

@Configuration
public class GlobalConfiguration {

    @Bean
    public InstantSource instantSource() {
        return Clock.systemUTC();
    }

}
