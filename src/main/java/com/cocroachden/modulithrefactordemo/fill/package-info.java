@ApplicationModule(allowedDependencies = {
        "infrastructure",
        "account :: domain",
        "account :: query",
        "account :: usecase",
        "contract :: domain",
        "contract :: query",
        "contract :: usecase",
        "agent :: domain",
        "agent :: query"
})
package com.cocroachden.modulithrefactordemo.fill;

import org.springframework.modulith.ApplicationModule;