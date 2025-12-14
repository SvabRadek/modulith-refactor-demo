package com.cocroachden.modulithrefactordemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RedisDemoApplicationTests {

    ApplicationModules modules = ApplicationModules.of(RedisDemoApplication.class);

    @Test
    public void verifyApplicationModule() {
        modules.verify();
    }

    @Test
    public void generateDocumentation() {
        new Documenter(modules)
                .writeModuleCanvases()
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml()
                .writeAggregatingDocument();
    }
}
