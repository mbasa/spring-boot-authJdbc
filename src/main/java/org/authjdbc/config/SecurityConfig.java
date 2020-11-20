package org.authjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by suman.das on 11/28/18.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.signing-key}")
    private String signingKey;

    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Value("${security.security-realm}")
    private String securityRealm;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm)
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        
        return converter;
    }

    // Spring has UserDetailsService interface, which can be overriden 
    // to provide our implementation for fetching user from database 
    // (or any other source).
    // The UserDetailsService object is used by the auth manager to load 
    // the user from database.
    // In addition, we need to define the password encoder also. 
    // So, auth manager can compare and verify passwords.
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());        
        
    }


    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(jdbcTemplate.getDataSource());
    }

    //Making this primary to avoid any accidental duplication with 
    //another token service instance of the same name
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        
        return defaultTokenServices;
    }

}
/*
@Autowired
public void configure(AuthenticationManagerBuilder auth, 
        DataSource dataSource) throws Exception {

   //auth.inMemoryAuthentication()
   // .withUser("admin").password(passwordEncoder().encode("secret")).roles("ADMIN").and()
   // .withUser("user").password(passwordEncoder().encode("secret")).roles("USER");
    

    auth.jdbcAuthentication()
        .dataSource(dataSource);
        //.passwordEncoder(passwordEncoder());
}
*/

