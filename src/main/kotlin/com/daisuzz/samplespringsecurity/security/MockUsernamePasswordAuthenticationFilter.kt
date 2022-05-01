package com.daisuzz.samplespringsecurity.security

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MockUsernamePasswordAuthenticationFilter : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val username = super.obtainUsername(request) ?: ""
        val password = super.obtainPassword(request) ?: ""
        val role = obtainRole(request) ?: ""
        val authRequest = MockAuthenticationToken(role, username, password)

        setDetails(request, authRequest)

        return this.authenticationManager.authenticate(authRequest)
    }

    private fun obtainRole(request: HttpServletRequest): String? {
        return request.getParameter("role")
    }
}
