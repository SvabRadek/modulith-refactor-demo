@ApplicationModule(
        allowedDependencies = {
                "infrastructure",
                "account",
                "account :: usecase",
                "contract",
        }
)
package com.cocroachden.modulithrefactordemo.agent;

import org.springframework.modulith.ApplicationModule;