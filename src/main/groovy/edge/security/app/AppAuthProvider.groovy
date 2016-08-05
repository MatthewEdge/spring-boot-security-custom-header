package edge.security.app

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

/**
 * AuthenticationProvider to fetch any additional details for the given
 * Authentication and validate it is an authorized user.
 *
 * Keep this logic here to keep the filter agnostic
 *
 * @author medge
 */
@Component
class AppAuthProvider implements AuthenticationProvider {

  @Override
  Authentication authenticate(Authentication authentication) throws AuthenticationException {
    AppAuthToken auth = (AppAuthToken) authentication

    // Extract additional details for the given Authentication
    // Usually involves a call to a DB or LDAP-type system. Customize as fits the app
    // AppUser userWithDetails = repository.findByUserId(auth.authUser.authId)

    // Set authenticated to true for the rest of Spring Security
    auth.authenticated = true

    return auth
  }

  /**
   * Determines if this AuthenticationProvider can validate the given
   * Authentication object
   *
   * @param authentication Authentication
   * @return boolean
   */
  @Override
  boolean supports(Class<?> authentication) {
    return AppAuthToken.class.isAssignableFrom(authentication)
  }
}
