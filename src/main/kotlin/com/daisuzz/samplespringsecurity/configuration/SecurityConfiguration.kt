package com.daisuzz.samplespringsecurity.configuration

import com.daisuzz.samplespringsecurity.configuration.SecurityConfiguration.MockUsernamePasswordAuthenticationFilterConfigurer.Companion.mockUsernamePasswordAuthenticationFilter
import com.daisuzz.samplespringsecurity.security.MockAuthenticationProvider
import com.daisuzz.samplespringsecurity.security.MockUsernamePasswordAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher


@Configuration
class SecurityConfiguration {

    @Bean
    fun filterChain(
        http: HttpSecurity,
        mockAuthenticationProvider: MockAuthenticationProvider,
    ): SecurityFilterChain {
        http.apply(mockUsernamePasswordAuthenticationFilter())
        http.authenticationProvider(mockAuthenticationProvider)
        http {
            authorizeHttpRequests {
                authorize("/admin/**", authenticated)
                authorize(anyRequest, permitAll)
            }
            logout {
                logoutUrl = "/logout"
                permitAll()
            }
            exceptionHandling {
                authenticationEntryPoint = LoginUrlAuthenticationEntryPoint("/login")
            }
        }

        return http.build()
    }

    class MockUsernamePasswordAuthenticationFilterConfigurer :
        AbstractHttpConfigurer<MockUsernamePasswordAuthenticationFilterConfigurer, HttpSecurity>() {

        override fun configure(http: HttpSecurity) {
            val authenticationManager = http.getSharedObject(AuthenticationManager::class.java)
            val filter = MockUsernamePasswordAuthenticationFilter()
            filter.setAuthenticationManager(authenticationManager)
            filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/login", "POST"))
            filter.setAuthenticationFailureHandler(SimpleUrlAuthenticationFailureHandler("/login?error"))
            http.addFilter(filter)
        }

        companion object {
            fun mockUsernamePasswordAuthenticationFilter(): MockUsernamePasswordAuthenticationFilterConfigurer {
                return MockUsernamePasswordAuthenticationFilterConfigurer()
            }
        }
    }
}
