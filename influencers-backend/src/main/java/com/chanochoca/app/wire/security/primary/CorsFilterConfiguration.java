package com.chanochoca.app.wire.security.primary;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// This class is used to ensure that CORS policies are applied consistently throughout the application.
@Configuration
public class CorsFilterConfiguration {

  private final CorsConfiguration corsConfiguration;

  public CorsFilterConfiguration(CorsConfiguration corsConfiguration) {
    this.corsConfiguration = corsConfiguration;
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Highest priority to execute this code
    return bean;
  }
}
