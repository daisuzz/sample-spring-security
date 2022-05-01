package com.daisuzz.samplespringsecurity.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class MockAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val authority = MockGratedAuthority((authentication as MockAuthenticationToken).role)
        val mockUser = User(authentication.name, authentication.credentials.toString(), listOf(authority))
        return MockAuthenticationToken(authority.role, mockUser, "", mockUser.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == MockAuthenticationToken::class.java
    }
}
