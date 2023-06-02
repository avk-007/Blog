package com.blog3.blog3.config;

import com.blog3.blog3.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //"/api/v1/auth/**",
    public static final String[] PUBLIC_URLS = { "/api/**","/api/auth/**","/v3/api-docs", "/v2/api-docs",
            "/swagger-resources/**", "/swagger-ui/**", "/webjars/**"
    };
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //add bean of auhentication
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //add 2 3 api docs for swagger antmatcherrss
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
//                //.antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
//
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
//added auth signin this is added lastly in security config
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
/// this is stopped because we are now using the signin with auth
    //    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails ramesh = User.builder().username("ramesh").password(passwordEncoder()
//                .encode("password")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder()
//                .encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(ramesh, admin);
//    }
}

