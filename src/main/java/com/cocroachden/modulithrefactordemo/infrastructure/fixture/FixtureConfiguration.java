package com.cocroachden.modulithrefactordemo.infrastructure.fixture;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.fixture")
@Getter
@Setter
public class FixtureConfiguration {
    private Boolean enabled = true;
    private List<String> tags;
}
