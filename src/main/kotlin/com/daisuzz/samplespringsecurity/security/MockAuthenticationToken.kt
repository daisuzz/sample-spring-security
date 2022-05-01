package com.daisuzz.samplespringsecurity.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class MockAuthenticationToken(
    val role: String,
    principal: Any,
    credentials: Any,
    authorities: Collection<GrantedAuthority?>? = mutableListOf()
) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)
