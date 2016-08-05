package edge.security

import edge.security.app.AppAuthProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

/**
 * Web Security Configuration. This is what injects
 * @author medge
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {

  // Autowired because of the @Value in the filter
  @Autowired HeaderAuthFilter headerAuthFilter
  @Autowired AppAuthProvider appAuthProvider

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(appAuthProvider)
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      // No basic authentication required
      .httpBasic().disable()

      // Inject custom HeaderAuthFilter into the FilterChain
      .addFilterBefore(headerAuthFilter, BasicAuthenticationFilter)

      // And finally enforce that all requests are authenticated first
      // Customize as necessary
      .authorizeRequests()
        .anyRequest().authenticated()
  }
}
