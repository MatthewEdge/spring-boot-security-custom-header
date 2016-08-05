package edge.security

import edge.security.app.AppAuthToken
import edge.security.app.AppUser
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Security Filter which validates required Security headers BEFORE passing the
 * request to an AuthenticationProvider. Basically a pre-check and Authentication
 * creator
 *
 * @author medge
 */
@Component
@Slf4j
class HeaderAuthFilter extends OncePerRequestFilter {

  @Value('${app.debug.enabled}')
  boolean debugEnabled

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

    // Short Circuit if debugging is enabled
    if(debugEnabled) {
      log.debug("Skipping Header Auth Filter because of Debug flag")

      // TODO mock Authentication instance must be injected to SecurityContext

      filterChain.doFilter(request, response)
    }

    // Validate required header(s) are present
    Optional<String> authHeader = Optional.ofNullable(request.getHeader("Authorization"))

    if(!authHeader.isPresent()) {
      throw new SecurityException("Authorization header is missing!")
    }

    // All is well, generate the Auth Token and inject into SecurityContext
    Authentication auth = new AppAuthToken(
      new AppUser(authHeader.get())
    )

    SecurityContextHolder.getContext().setAuthentication(auth)

    log.debug("Header Auth Successful for ${auth.authUser.authId}")
    filterChain.doFilter(request, response)
  }
}
