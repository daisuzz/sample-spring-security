package com.daisuzz.samplespringsecurity.security

import org.springframework.security.core.GrantedAuthority

class MockGratedAuthority(val role: String) : GrantedAuthority {
    override fun getAuthority(): String {
        return role
    }
}
