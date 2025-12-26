@ApplicationModule(
        allowedDependencies = {"contract :: domain", "contract :: query", "infrastructure", "contract :: usecase"}
)
package com.cocroachden.modulithrefactordemo.account;

import org.springframework.modulith.ApplicationModule;