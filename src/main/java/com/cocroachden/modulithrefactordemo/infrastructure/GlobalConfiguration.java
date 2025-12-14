package com.cocroachden.modulithrefactordemo.infrastructure;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
import java.time.Clock;
import java.time.InstantSource;

@Configuration
public class GlobalConfiguration {

    @Profile("!test")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp",
                "-tcpAllowOthers",
                "-tcpPort", "9092"
        );
    }

    @Bean
    public InstantSource instantSource() {
        return Clock.systemUTC();
    }

}
