@ApplicationModule(
        allowedDependencies = {
                "infrastructure",
                "account :: domain",
                "account :: usecase",
                "contract :: domain",
        }
)
package com.cocroachden.modulithrefactordemo.agent;

import org.springframework.modulith.ApplicationModule;