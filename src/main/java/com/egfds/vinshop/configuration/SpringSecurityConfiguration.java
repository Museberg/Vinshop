package com.egfds.vinshop.configuration;

import com.egfds.vinshop.services.UserAuthDetailsService;
import com.egfds.vinshop.utillities.PasswordEncoderTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.net.PasswordAuthentication;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Qualifier("userAuthDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("blah")
//                .password("blah")
//                .roles("USER")
//                .and()
//                .withUser("foo")
//                .password("foo")
//                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoderTest();
      //  return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable();
        httpSecurity
            .authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN", "OWNER")
                .and()
            .authorizeRequests()
                .antMatchers("/products/view/**")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/products/allProducts")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/products/**")
                .hasAnyRole("ADMIN", "OWNER")
                .and()
            .authorizeRequests()
                .antMatchers("/user/**")
                .hasAnyRole("USER", "ADMIN", "OWNER")
                .and()
            .authorizeRequests()
                .antMatchers("/owner/aboutFarm/**")
                .hasAnyRole("ADMIN", "OWNER")
                .and()
            .authorizeRequests()
                .antMatchers("/owner/updateRoles/**")
                .hasAnyRole( "OWNER")
                .and()
            .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
            .authorizeRequests()
                .antMatchers("/error")
                .permitAll()
                .and()
            .formLogin();
    }

}
