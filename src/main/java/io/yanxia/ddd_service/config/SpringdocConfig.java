package io.yanxia.ddd_service.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfig {
    
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("springdoc").pathsToMatch("/api/**").build();
    }

}
