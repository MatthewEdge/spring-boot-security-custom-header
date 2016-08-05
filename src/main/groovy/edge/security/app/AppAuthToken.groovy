package edge.security.app

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * Auth Token which contains the pre-authenticated credentials/principal object
 * that is injected into the SecurityContext
 *
 * @author medge
 */
class AppAuthToken extends AbstractAuthenticationToken {

  AppUser authUser

  /**
   * By default the User's role is Roles.APP_USER
   *
   * @param authUser
   */
  AppAuthToken(AppUser authUser) {
    this(
      Collections.singletonList(Roles.APP_USER),
      authUser
    )
  }

  AppAuthToken(Collection<? extends GrantedAuthority> authorities, AppUser authUser) {
    super(authorities)

    this.authUser = authUser
  }

  AppUser getAuthUser() {
    return authUser
  }

  @Override
  Object getCredentials() {
    return authUser
  }

  @Override
  Object getPrincipal() {
    return authUser
  }
}
