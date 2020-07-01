package com.example.CafeGoogooExample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

/**
 * Configures Spring Boot security using MySQL queries and assigns roles and authorizations
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //Auto-generated bean to handle DataSource data
    @Autowired
    private DataSource dataSource;

    //Bean created to handle password encoding and password comparison
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Basic Authentication using MySQL queries to determine if the user exists,then returning the user's role
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                //checks the data source, rather than using an in-memory repository
                .dataSource(dataSource)
                //checks for users by username, then by authority (role)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?");
    }

    // Secures the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ResponseEntity.ok(http
                .httpBasic()
                .and()
                .authorizeRequests()
                //allows admin and user to access the get mapping from the menu, used to determine roles when user signs in
                .antMatchers(HttpMethod.GET, "/menu/**").hasAnyRole( "ADMIN", "USER")
                .and()
                .csrf().disable()
                .formLogin().disable());
    }





}
