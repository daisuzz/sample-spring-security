package com.daisuzz.samplespringsecurity.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class MockAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val authority = MockGratedAuthority((authentication as MockAuthenticationToken).role)
        try {
            val mockUser = User(authentication.name, authentication.credentials.toString(), listOf(authority))
            return MockAuthenticationToken(authority.role, mockUser, "", mockUser.authorities)
        } catch (ex: Exception) {
            throw BadCredentialsException("Bad credential")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == MockAuthenticationToken::class.java
    }
}
