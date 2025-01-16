package com.chanochoca.app.wire.security.primary;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class CorsProperties {

  @Bean
  @ConfigurationProperties(prefix = "application.cors", ignoreUnknownFields = false)
  // application.cors is a property in application.yml (external property)
  // CorsConfiguration contains the necessary settings to enable and control CORS policies.
  public CorsConfiguration corsConfiguration() {
    return new CorsConfiguration();
  }
}
