package com.example.springbootmvc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@EnableWebSecurity
class Security : WebSecurityConfigurerAdapter() {

    @Autowired
    private val data: DataSource? = null
    @Autowired
    private val encoder: PasswordEncoder? = null

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder(8)
    }

    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication()
            .dataSource(data)
            .passwordEncoder(encoder)
            .usersByUsernameQuery("select username, password, 'true' from users where username=?")
            .authoritiesByUsernameQuery("select username, role from users where username=?")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/app/list").authenticated()
            .antMatchers("/app/**").hasRole("ADMIN")
            .antMatchers("/api/**").hasAnyRole("ADMIN", "API")
            .and()
            .formLogin()
            .and()
            .csrf().disable()
    }
}