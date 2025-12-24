@ApplicationModule(allowedDependencies = {
        "infrastructure",
        "contract :: domain",
        "contract :: query",
        "contract :: usecase",
        "account :: domain",
        "account :: query",
        "account :: usecase"
})
package com.cocroachden.modulithrefactordemo.fill;

import org.springframework.modulith.ApplicationModule;