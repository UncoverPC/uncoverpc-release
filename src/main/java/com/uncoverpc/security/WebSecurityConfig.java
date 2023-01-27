package com.uncoverpc.security;

import com.google.api.Http;
import com.uncoverpc.security.user.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

import java.security.Security;

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

    @Bean UserDetailsService OAuthUserDetailsService() {return new CustomOAuth2UserService(); }

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

    @Bean DaoAuthenticationProvider OAuthProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(OAuthUserDetailsService());
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

    /*
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.authenticationProvider(authenticationProvider());
        authManagerBuilder.authenticationProvider(OAuthProvider());
        return authManagerBuilder.build();
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
    protected SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.cors();
        http.headers().xssProtection();
        http.authenticationProvider(authenticationProvider());
        //http.authorizeRequests().antMatchers("/", "/login", "/callback/", "/webjars/**", "/error/**").permitAll();

        http.authorizeRequests().antMatchers("/admin/*").hasAuthority(Roles.Role.ADMIN.toString()).and().formLogin()
                .failureHandler(authenticationFailureHandler()).loginPage("/login").defaultSuccessUrl("/admin/dashboard")
                .usernameParameter("email").defaultSuccessUrl("/admin/dashboard");
        http.authorizeRequests().antMatchers("/user/*").hasAuthority(Roles.Role.USER.toString()).and().formLogin().loginPage("/login")
                .usernameParameter("email").defaultSuccessUrl("/user/dashboard");
        http.authorizeRequests().antMatchers("/user/*").hasAnyRole().and().oauth2Login().loginPage("/login").authorizationEndpoint(authorization -> authorization
                .baseUri("/login/oauth2/code"));
        http.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID", "jwt");
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
