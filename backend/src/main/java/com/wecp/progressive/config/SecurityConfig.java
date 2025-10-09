package com.wecp.progressive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wecp.progressive.jwt.JwtRequestFilter;
import com.wecp.progressive.service.impl.UserLoginServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private UserLoginServiceImpl userLoginServiceImpl;

    private JwtRequestFilter jwtRequestFilter;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserLoginServiceImpl userLoginServiceImpl,JwtRequestFilter jwtRequestFilter,PasswordEncoder passwordEncoder)
    {
        this.userLoginServiceImpl = userLoginServiceImpl;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userLoginServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                // .anyRequest().permitAll() // Allow all requests without authentication
                .antMatchers(HttpMethod.POST,"/user/register","/user/login").permitAll()
                .antMatchers(HttpMethod.GET,"/team","/team/**","/cricketer","/cricketer/**","/match","/match**").hasAnyAuthority("user","admin") //
                .antMatchers(HttpMethod.POST,"/vote","/ticket").hasAuthority("user")
                .antMatchers(HttpMethod.POST,"/vote","/team/**","/cricketer","/match").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT,"/team/**","/cricketer/**","/match/**").hasAuthority("admin")
                .antMatchers(HttpMethod.DELETE,"/team/**","/cricketer/**","/match/**").hasAuthority("admin")
                .antMatchers(HttpMethod.GET,"/vote/**","/ticket/**").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .csrf().disable() // Disable CSRF protection if it's not needed
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}