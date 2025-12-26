@ApplicationModule(
        allowedDependencies = {
                "infrastructure",
                "fill :: domain",
                "account :: domain",
                "contract :: domain",
                "fill :: usecase"
        }
)
package com.cocroachden.modulithrefactordemo.agent;

import org.springframework.modulith.ApplicationModule;