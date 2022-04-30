package com.daisuzz.samplespringsecurity.configuration

import com.daisuzz.samplespringsecurity.security.MockAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(
        http: HttpSecurity,
        mockAuthenticationProvider: MockAuthenticationProvider
    ): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize(anyRequest, authenticated)
            }
            formLogin { }
            logout {
                logoutUrl = "/logout"
            }
        }
        http.authenticationProvider(mockAuthenticationProvider)

        return http.build()
    }
}
