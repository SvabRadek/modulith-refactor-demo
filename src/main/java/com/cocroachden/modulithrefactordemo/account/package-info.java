@ApplicationModule(
        allowedDependencies = {
                "contract",
                "contract :: query" ,
                "infrastructure"
        }
)
package com.cocroachden.modulithrefactordemo.account;

import org.springframework.modulith.ApplicationModule;