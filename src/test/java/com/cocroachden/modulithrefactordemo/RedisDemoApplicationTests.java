package com.cocroachden.modulithrefactordemo;

import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.core.VerificationOptions;
import org.springframework.modulith.docs.Documenter;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RedisDemoApplicationTests {

    ApplicationModules modules = ApplicationModules.of(RedisDemoApplication.class);

    @Test
    public void verifyApplicationModule() {
        var layered = JMoleculesArchitectureRules.ensureLayeringStrict();
        var options = VerificationOptions.defaults()
                .withAdditionalVerifications(layered);
        modules.verify(options);
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
