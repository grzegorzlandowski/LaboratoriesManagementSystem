package pk.engineeringthesis.laboratoriesmanagementsystem.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/gfx/logopk.png","/rejestracja","/rejestracja/*","/upload","/deleteimage","/gfx/*","/api/*","/api/events","/api/events/create","/css/*","/js/*","/getcount")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll() .loginPage("/login").defaultSuccessUrl("/")
                .and()
                .logout().permitAll()
                .and().exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .csrf().disable();



    }
}