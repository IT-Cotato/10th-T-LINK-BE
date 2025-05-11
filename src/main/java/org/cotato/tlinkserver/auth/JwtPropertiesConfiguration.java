package org.cotato.tlinkserver.auth;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(basePackages = "org.cotato.tlinkserver.auth")
public class JwtPropertiesConfiguration {
}
