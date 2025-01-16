package com.chanochoca.app.shared.authentication.infrastructure.primary;

import com.chanochoca.app.shared.authentication.application.AuthenticatedUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KindeJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt source) {
    // JwtAuthenticationToken sirve para verificar la identidad del usuario y sus permisos
    // JwtGrantedAuthoritiesConverter sirve para extraer los GrantedAuthority de los atributos del ámbito típicamente encontrados en un JWT
    return new JwtAuthenticationToken(source,
      Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).stream(), extractResourceRoles(source).stream()).collect(Collectors.toSet()));
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    return AuthenticatedUser.extractRolesFromToken(jwt).stream()
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toSet());
  }
}
