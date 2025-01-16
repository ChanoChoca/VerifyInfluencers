package com.chanochoca.app.wire.postgresql.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // Enable Transaction management
@EnableJpaRepositories(basePackages = {"com.chanochoca.app"}) // Scan classes that extend from JpaRepository or CrudRepository
@EnableJpaAuditing // Enables audit support: @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
 // EnableSpringDataWebSupport will indicate that pagination data should be serialized using DTOs instead of directly exposing JPA entities.
public class DatabaseConfiguration {
}
