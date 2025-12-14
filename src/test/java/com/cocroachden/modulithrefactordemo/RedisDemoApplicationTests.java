package com.cocroachden.modulithrefactordemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class RedisDemoApplicationTests {



    @Test
    void contextLoads() {
    }

    @Test
    public void generateDocumentation() {
        ApplicationModules.of(RedisDemoApplication.class)
                .verify();
    }



}
