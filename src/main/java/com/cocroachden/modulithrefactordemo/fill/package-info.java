@ApplicationModule(allowedDependencies = {
        "infrastructure",
        "account",
        "account :: query",
        "account :: usecase",
        "contract",
        "contract :: query",
        "contract :: usecase",
        "agent",
        "agent :: query"
})
package com.cocroachden.modulithrefactordemo.fill;

import org.springframework.modulith.ApplicationModule;