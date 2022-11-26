package com.uncoverpc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.uncoverpc.jwt.JwtRequestFilter;
import com.uncoverpc.model.user.Roles;
import com.uncoverpc.security.exception.authentication.CustomAuthenticationFailureHandler;
import com.uncoverpc.security.user.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    @Autowired
    private CustomUserDetailsService myUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = Roles.Role.DEV.toString() + " > " + Roles.Role.ADMIN + " \n " + Roles.Role.ADMIN + " > "
                + Roles.Role.TEST + " \n " + Roles.Role.TEST + " > " + Roles.Role.USER;
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
    
    // @Bean
    // public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
    //     DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
    //     expressionHandler.setRoleHierarchy(roleHierarchy());
    //     return expressionHandler;
    // }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.cors();
        http.headers().xssProtection();
        http.authenticationProvider(authenticationProvider());
        http.authorizeRequests().antMatchers("/admin/*").hasRole(Roles.Role.ADMIN.toString()).and().formLogin()
                .failureHandler(authenticationFailureHandler()).loginPage("/login")
                .usernameParameter("email").defaultSuccessUrl("/admin/dashboard");
        http.authorizeRequests().antMatchers("/user/*").hasAnyRole().and().formLogin().loginPage("/login")
                .usernameParameter("email").defaultSuccessUrl("/user/dashboard");
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID", "jwt");
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
