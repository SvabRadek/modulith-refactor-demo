package com.cocroachden.modulithrefactordemo;

import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.jmolecules.archunit.JMoleculesDddRules;
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
        var options = VerificationOptions.defaults()
                .withAdditionalVerifications(JMoleculesArchitectureRules.ensureLayeringStrict())
                .withAdditionalVerifications(JMoleculesDddRules.all());
        modules.verify(options);
    }

    @Test
    public void generateDocumentation() {
//        new Documenter(modules)
//                .writeModuleCanvases()
//                .writeModulesAsPlantUml()
//                .writeIndividualModulesAsPlantUml()
//                .writeDocumentation(
//                        Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.C4), Documenter.CanvasOptions.defaults())
//                .writeAggregatingDocument();

        new Documenter(modules)
                .writeDocumentation(
                        Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.C4), Documenter.CanvasOptions.defaults()
                );
    }
}
